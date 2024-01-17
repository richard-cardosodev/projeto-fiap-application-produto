package br.fiap.projeto.produto.bdd;

import br.fiap.projeto.produto.adapter.controller.rest.request.ProdutoDTORequest;
import br.fiap.projeto.produto.adapter.controller.rest.response.ProdutoDTOResponse;
import br.fiap.projeto.produto.entity.enums.CategoriaProduto;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class StepDefinitions {

    private Response response;

    private ProdutoDTOResponse produtoResponse;

    private final String ENDPOINT_API_CRIAR_PRODUTO = "http://localhost:8080/produto/produtos";

    @Quando("submeter um novo produto")
    public ProdutoDTOResponse submeterNovoProduto() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(geraProdutoRequest())
                .when()
                .post(ENDPOINT_API_CRIAR_PRODUTO);
        return response.then().extract().as(ProdutoDTOResponse.class);
    }

    @Então("o produto é registrado com sucesso")
    public void produtoRegistradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ProdutoDTOResponseSchema.json"));
    }

    @Dado("que um produto já foi registrado")
    public void produtoJaRegistrado() {
        produtoResponse = submeterNovoProduto();
    }

    @Quando("quando requisitar a alteração do produto")
    public void requisitarAlteracaoProduto() {
        produtoResponse.setNome("Sprite" + LocalDateTime.now().toString());
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(produtoResponse)
                .when()
                .put("/produto/produtos/{id}", produtoResponse.getCodigo().toString());
    }

    @Então("o produto é alterado com sucesso")
    public void produtoAlteradoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Quando("requisitar a exclusão do produto")
    public void requisitarExclusaoDoProduto() {
        response = given()
                .when()
                .delete("/produto/produtos/{id}", produtoResponse.getCodigo().toString());
    }

    @Então("o produto é excluido com sucesso")
    public void produtoExcluidoComSucesso() {
        response.then().statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Quando("requisitar a busca do produto")
    public void requisitarBuscaProduto() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/produto/produtos/{id}", produtoResponse.getCodigo().toString());
    }

    @Então("o produto é exibido com sucesso")
    public void produtoExibidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ProdutoDTOResponseSchema.json"));
    }

    @Quando("requisitar a lista de produtos")
    public void requisitarListaDeProdutos() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/produto/produtos");
    }

    @Então("os produtos são exibidos com sucesso")
    public void produtosExibidosComSucesso() {
        response.then()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ListProdutoDTOResponseSchema.json"));
    }

    @Dado("que um produto já foi registrado na categoria buscada")
    public void produtoJaRegistradoNaCategoriaBuscada() {
        produtoResponse = submeterNovoProduto();
    }

    @Quando("requisitar a busca por produtos da categoria")
    public void requisitarBuscaPorProdutosDaCategoria() {
        response = given()
                .when()
                .queryParam("categoria", CategoriaProduto.BEBIDA)
                .get("/produto/produtos/por-categoria");
    }

    @Dado("que lista de categorias exista")
    public void listaDeCategoriasExiste() {
        Assertions.assertTrue(CategoriaProduto.values().length > 0);
    }

    @Quando("requisitar a sua listagem")
    public void requisitarSuaListagem() {
//        Arrays.stream(CategoriaProduto.values()).forEach(System.out::println);
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/produto/produtos/categorias");
    }

    @Então("as categorias são exibidas")
    public void asCategoriasSaoExibidas() {
        response.then()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ListCategoriasResponseSchema.json"));
    }

    private ProdutoDTORequest geraProdutoRequest() {
        return ProdutoDTORequest
                .builder()
                .nome("Coca Cola" + LocalDateTime.now().toString())
                .descricao("Refrigerante")
                .preco(10d)
                .categoria(CategoriaProduto.BEBIDA.name())
                .imagem("http://teste")
                .tempoPreparoMin(15)
                .build();
    }

}
