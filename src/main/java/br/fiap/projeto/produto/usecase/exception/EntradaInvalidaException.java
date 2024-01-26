package br.fiap.projeto.produto.usecase.exception;

public class EntradaInvalidaException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Entrada inválida!";
    private static final int CODE = 3003;
    public EntradaInvalidaException() {
        super(CODE, DEFAULT_MESSAGE);
    }
    public EntradaInvalidaException(String message) {
        super(CODE, message);
    }
}
