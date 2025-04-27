package br.com.tacontigo.exceptions;

public class QuestaoException extends RuntimeException {
    private final String code;

    public QuestaoException(String message, String code) {
        super(message);
        this.code = code;
    }

     public QuestaoException(String message) {
        super(message);
        this.code = "";
    }

    public String getCode() {
        return code;
    }
}