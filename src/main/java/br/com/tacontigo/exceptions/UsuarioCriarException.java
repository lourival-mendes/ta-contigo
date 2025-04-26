package br.com.tacontigo.exceptions;

public class UsuarioCriarException extends RuntimeException {
    private final String code;

    public UsuarioCriarException(String message, String code) {
        super(message);
        this.code = code;
    }

     public UsuarioCriarException(String message) {
        super(message);
        this.code = "";
    }

    public String getCode() {
        return code;
    }
}