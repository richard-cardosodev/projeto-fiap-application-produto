package br.fiap.projeto.produto.external.configuration;

import br.fiap.projeto.produto.entity.Produto;
import br.fiap.projeto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.produto.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.produto.usecase.port.IProdutoRepositoryAdapterGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Configuration
public class PostgresProdutoDataLoader {

    public static final String IMAGE_URL = "https://via.placeholder.com/200/200";
    @Autowired
    private IProdutoRepositoryAdapterGateway produtoAdapterGateway;

    @PostConstruct
    public void init() throws EntradaInvalidaException {
        List<Produto> list = Arrays.asList(
                new Produto("28894d3e-5f18-40da-93c7-49440b91f36b", "Podrão", "Tudo que tem direito", 17.0,
                        CategoriaProduto.LANCHE, IMAGE_URL, 12),
                new Produto("abfab667-a450-479d-add8-97af31af3537", "Podrão Plus", "Tudo que tem direito ao quadrado",
                        20.0, CategoriaProduto.LANCHE, IMAGE_URL, 17),
                new Produto("c1ce3d72-78e3-4b36-8a57-e26d1a099ae8", "Podrão light", "Deixa você com fome ainda", 9.50,
                        CategoriaProduto.LANCHE, IMAGE_URL, 10),
                new Produto("56d6f0c4-47bf-4434-be73-932fa31ba9a1", "Cola Cola", "Refrigerante de cola", 6.5,
                        CategoriaProduto.BEBIDA, IMAGE_URL, 1),
                new Produto("9e45995a-fe23-4a18-9255-59c0ea562b4f", "Dolly Guaraná", "O mais puro suor de pedreiro",
                        1.0, CategoriaProduto.BEBIDA, IMAGE_URL, 1),
                new Produto("60ed74bb-e422-4027-9e8c-f66a69110829", "Batata Frita", "Feito de batata mesmo", 13.5,
                        CategoriaProduto.ACOMPANHAMENTO, IMAGE_URL, 10),
                new Produto("584bb842-222e-4abb-8e31-4265391026b9", "Sorvete de tamarindo",
                        "Sorvete de tamarindo que parece de abacate, mas tem gosto de limão", 5.5,
                        CategoriaProduto.SOBREMESA, IMAGE_URL, 5));
        list.stream().forEach(p -> {
            try {
                produtoAdapterGateway.criaProduto(p);
            } catch (EntradaInvalidaException e) {
                e.printStackTrace();
            }
        });
    }
}
