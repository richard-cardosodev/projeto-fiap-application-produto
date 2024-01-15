package br.fiap.projeto.produto.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class StepDefinitions {

    private final String ENDPOINT_API_CRIAR_PRODUTO = "http://localhost:8080/produto/produtos";
    private String jsonProduto = "{\"nome\": \"Produto 1\",\"descricao\": \"Descrição produto 1\",\"preco\": 10,\"categoria\": \"BEBIDA\",\"imagem\": \"url da imagem\",\"tempoPreparoMin\": 15}";

    @Given("dado que eu recebi os dados para preenchimento de um produto")
    public void dado_que_eu_recebi_os_dados_para_preenchimento_de_um_produto() {
        System.out.println("Recebi os dados de um produto: " + jsonProduto);
    }
    @When("irei preencher um registro de produto no banco de dados")
    public void irei_preencher_um_registro_de_produto_no_banco_de_dados() {
       criaProduto(jsonProduto);
    }
    @Then("recebo a resposta com um registro de produto com o código de identificação gerado na persistencia")
    public void recebo_a_resposta_com_um_registro_de_produto_com_o_código_de_identificação_gerado_na_persistencia() {
        System.out.println("recebi a resposta da criação");
    }
    @Given("dado que eu tenho o registro de um produto")
    public void dado_que_eu_tenho_o_registro_de_um_produto() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("quando eu receber os dados dos campos que devem ser alterados")
    public void quando_eu_receber_os_dados_dos_campos_que_devem_ser_alterados() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("efetuo a alteração dos campos no produto")
    public void efetuo_a_alteração_dos_campos_no_produto() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("retorno um código de sucesso")
    public void retorno_um_código_de_sucesso() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("quando eu receber o código deste produto")
    public void quando_eu_receber_o_código_deste_produto() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("efetuo a remoção do produto")
    public void efetuo_a_remoção_do_produto() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("executo a busca do produto")
    public void executo_a_busca_do_produto() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("retorno os dados do produto")
    public void retorno_os_dados_do_produto() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("dado que recebo uma requisição para listar todos os produtos")
    public void dado_que_recebo_uma_requisição_para_listar_todos_os_produtos() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("executo a busca por todos os produtos")
    public void executo_a_busca_por_todos_os_produtos() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("retorno a listagem dos dados dos produtos encontrados")
    public void retorno_a_listagem_dos_dados_dos_produtos_encontrados() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("dado que eu tenho o registro de produtos nesta categoria solicitada")
    public void dado_que_eu_tenho_o_registro_de_produtos_nesta_categoria_solicitada() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("quando eu receber a identificação da categoria")
    public void quando_eu_receber_a_identificação_da_categoria() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("executo a busca dos produtos")
    public void executo_a_busca_dos_produtos() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("retorno a listagem dos produtos encontrados")
    public void retorno_a_listagem_dos_produtos_encontrados() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("dado que recebo uma requisição para listar todas as categorias de produtos")
    public void dado_que_recebo_uma_requisição_para_listar_todas_as_categorias_de_produtos() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("executo a busca por todas as categorias de produtos")
    public void executo_a_busca_por_todas_as_categorias_de_produtos() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("retorno a listagem das categorias encontradas")
    public void retorno_a_listagem_das_categorias_encontradas() {
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
