package br.fiap.projeto.produto.adapter.controller.rest.request;

import br.fiap.projeto.produto.entity.Produto;
import br.fiap.projeto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.produto.usecase.exception.EntradaInvalidaException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ProdutoDTORequest {

    private String nome;

    private String descricao;

    private Double preco;

    private String categoria;

    private String imagem;

    private Integer tempoPreparoMin;

    public Produto toProduto() throws EntradaInvalidaException {
        return new Produto(nome, descricao, preco, CategoriaProduto.valueOf(categoria), imagem, tempoPreparoMin);
    }
}
