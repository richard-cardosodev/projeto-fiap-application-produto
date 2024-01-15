package br.fiap.projeto.produto.bdd;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class StepDefinitions {

    private final String ENDPOINT_API_CRIAR_PRODUTO = "http://localhost:8080/produto/produtos";
    private String jsonProduto = "{\"nome\": \"Produto 1\",\"descricao\": \"Descrição produto 1\",\"preco\": 10,\"categoria\": \"BEBIDA\",\"imagem\": \"url da imagem\",\"tempoPreparoMin\": 15}";

    @Quando("submeter um novo produto")
    public void submeter_um_novo_produto() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Então("o produto é registrado com sucesso")
    public void o_produto_é_registrado_com_sucesso() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Dado("que um produto já foi registrado")
    public void que_um_produto_já_foi_registrado() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Quando("quando requisitar a alteração do produto")
    public void quando_requisitar_a_alteração_do_produto() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Então("o produto é alterado com sucesso")
    public void o_produto_é_alterado_com_sucesso() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Quando("requisitar a exclusão do produto")
    public void requisitar_a_exclusão_do_produto() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Então("o produto é excluido com sucesso")
    public void o_produto_é_excluido_com_sucesso() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Quando("requisitar a busca do produto")
    public void requisitar_a_busca_do_produto() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Então("o produto é exibido com sucesso")
    public void o_produto_é_exibido_com_sucesso() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Quando("requisitar a lista de produtos")
    public void requisitar_a_lista_de_produtos() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Então("os produtos são exibidos com sucesso")
    public void os_produtos_são_exibidos_com_sucesso() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Dado("que um produto já foi registrado na categoria buscada")
    public void que_um_produto_já_foi_registrado_na_categoria_buscada() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Quando("requisitar a busca por produtos da categoria")
    public void requisitar_a_busca_por_produtos_da_categoria() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Dado("que lista de categorias exista")
    public void que_lista_de_categorias_exista() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Quando("requisitar a sua listagem")
    public void requisitar_a_sua_listagem() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Então("as categorias são exibidas")
    public void as_categorias_são_exibidas() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    private Integer criaProduto(String jsonProduto) {
        try {
            URL url = new URL(ENDPOINT_API_CRIAR_PRODUTO);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = jsonProduto;

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            return responseCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
