package br.com.tacontigo.exceptions;

public class UsuarioException extends RuntimeException {
    private final String code;

    public UsuarioException(String message, String code) {
        super(message);
        this.code = code;
    }

     public UsuarioException(String message) {
        super(message);
        this.code = "";
    }

    public String getCode() {
        return code;
    }
}