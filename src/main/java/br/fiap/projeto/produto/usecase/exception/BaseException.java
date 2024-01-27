package br.fiap.projeto.produto.usecase.exception;

public abstract class BaseException extends Exception {

    public static final int DEFAULT_CODE = 3000;
    private int code;
    private String message;

    protected BaseException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
