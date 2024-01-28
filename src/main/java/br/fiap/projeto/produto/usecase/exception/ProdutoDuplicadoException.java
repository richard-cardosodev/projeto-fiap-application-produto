package br.fiap.projeto.produto.usecase.exception;

public class ProdutoDuplicadoException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Produto já existe!";
    private static final int CODE = 3002;
    public ProdutoDuplicadoException(String message) {
        super(CODE, message);
    }
}
