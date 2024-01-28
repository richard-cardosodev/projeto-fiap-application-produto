package br.fiap.projeto.produto.usecase.exception;

import br.fiap.projeto.produto.entity.Produto;

public class ProdutoDuplicadoException extends BaseException {

    private Produto produtoExistente;
    private static final int CODE = 3002;
    public ProdutoDuplicadoException(String message, Produto produtoExistente) {
        super(CODE, message);
        this.produtoExistente = produtoExistente;
    }

    public Produto getProdutoExistente() {
        return produtoExistente;
    }
}
