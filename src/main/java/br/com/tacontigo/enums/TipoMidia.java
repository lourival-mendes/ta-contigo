package br.com.tacontigo.enums;

public enum TipoMidia {
    AUDIO("audio"),
    VIDEO("video"),
    PDF("pdf"),
    IMAGEM("imagem");

    private final String descricao;

    TipoMidia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static TipoMidia fromDescricao(String descricao) {
        for (TipoMidia tipo : TipoMidia.values()) {
            if (tipo.descricao.equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de mídia inválido: " + descricao);
    }
}
