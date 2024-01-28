package br.fiap.projeto.produto;

import br.fiap.projeto.produto.adapter.controller.rest.request.ProdutoDTORequest;
import br.fiap.projeto.produto.entity.enums.CategoriaProduto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Random;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testeInserir() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/produtos")
                        .content(asJsonString(geraProdutoRequestDTO()))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));

    }

    @Test
    void testeInserirComImagemInvalida() throws Exception {

        ProdutoDTORequest dto = geraProdutoRequestDTO();
        dto.setImagem("123");
        mvc.perform(MockMvcRequestBuilders
                        .post("/produtos")
                        .content(asJsonString(dto))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.PRECONDITION_FAILED.value()));
    }

    @Test
    void testeBuscaProdutoInexistente() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/produtos/1234")
        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void testeBuscaTodosProdutos() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/produtos")
        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
    }

    @Test
    void testeBuscaPorCategoria() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/produtos/por-categoria")
                .queryParam("categoria", CategoriaProduto.LANCHE.name())
        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
    }

    @Test
    void testRemoverInexistente() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/produtos/0000")
        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }

    private ProdutoDTORequest geraProdutoRequestDTO() {
        return ProdutoDTORequest.builder()
                .nome(gerarUsuarioAleatorio())
                .descricao("Descrição Produto1")
                .categoria(CategoriaProduto.LANCHE.name())
                .preco(12.5)
                .tempoPreparoMin(10)
                .imagem("http://teste.com/imagemProduto1.png")
                .build();
    }

    private static String gerarUsuarioAleatorio() {
        // Caracteres possíveis no nome de usuário
        String caracteres = "abcdefghijklmnopqrstuvwxyz1234567890";

        StringBuilder usuario = new StringBuilder();

        // Gera um nome de usuário aleatório com comprimento entre 6 e 12 caracteres
        int comprimentoUsuario = 6 + new Random().nextInt(7);
        for (int i = 0; i < comprimentoUsuario; i++) {
            int indiceCaractere = new Random().nextInt(caracteres.length());
            usuario.append(caracteres.charAt(indiceCaractere));
        }

        return usuario.toString();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
