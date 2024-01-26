package br.fiap.projeto.produto.usecase.exception;

public class ProdutoNaoEncontradoException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Produto não encontrado!";
    private static final int CODE = 3001;
    public ProdutoNaoEncontradoException() {
        super(CODE, DEFAULT_MESSAGE);
    }
    public ProdutoNaoEncontradoException(String message) {
        super(CODE, message);
    }
}
