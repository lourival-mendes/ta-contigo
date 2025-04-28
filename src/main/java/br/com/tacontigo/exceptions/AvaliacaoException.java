package br.com.tacontigo.exceptions;

public class AvaliacaoException extends RuntimeException {
    private final String code;

    public AvaliacaoException(String message, String code) {
        super(message);
        this.code = code;
    }

     public AvaliacaoException(String message) {
        super(message);
        this.code = "";
    }

    public String getCode() {
        return code;
    }
}