package br.com.tacontigo.enums;

public enum Genero {
    MASCULINO, FEMININO, OUTRO, NAO_INFORMADO;

    public static Genero fromString(String genero) {
        return Genero.valueOf(genero.toUpperCase());
    }

    public static String toString(Genero genero) {
        return genero.name();
    }
}
