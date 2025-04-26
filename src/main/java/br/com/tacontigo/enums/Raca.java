package br.com.tacontigo.enums;

public enum Raca {
    BRANCA, NEGRA, PARDA, AMARELA, INDIGENA, NAO_INFORMADO;

    public static Raca fromString(String raca) {
        return Raca.valueOf(raca.toUpperCase());
    }

    public static String toString(Raca raca) {
        return raca.name();
    }
}
