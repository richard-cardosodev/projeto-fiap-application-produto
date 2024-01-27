package br.fiap.projeto.produto.external.repository.mongo.collections;

import br.fiap.projeto.produto.entity.Produto;
import br.fiap.projeto.produto.entity.enums.CategoriaProduto;
import lombok.SneakyThrows;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@ToString
@Document(collection = "produtos")
public class ProdutoDocument {

    @Id
    private String codigo;
    private String nome;
    private String descricao;
    private Double preco;
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;
    private String imagem;
    private Integer tempoPreparoMin;

    public ProdutoDocument() {
    }

    public ProdutoDocument(Produto produto) {
        this.codigo = produto.getCodigo();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.categoria = produto.getCategoria();
        this.imagem = produto.getImagem();
        this.tempoPreparoMin = produto.getTempoPreparoMin();
    }

    @SneakyThrows
    public Produto toProduto() {
        return new Produto(codigo, nome, descricao, preco, categoria, imagem, tempoPreparoMin);
    }
}
