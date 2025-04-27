package br.com.tacontigo.enums;

public enum TipoQuestao {
    UMA_RESPOSTA_VERDADEIRA("uma_resposta_verdadeira"),
    DUAS_RESPOSTAS_VERDADEIRAS("duas_respostas_verdadeiras"),
    TRES_RESPOSTAS_VERDADEIRAS("tres_respostas_verdadeiras"),
    MULTIPLAS_RESPOSTAS_VERDADEIRAS("multiplas_respostas_verdadeiras"),
    UMA_RESPOSTA_FALSA("uma_resposta_falsa"),
    DUAS_RESPOSTAS_FALSAS("duas_respostas_falsas"),
    TRES_RESPOSTAS_FALSAS("tres_respostas_falsas"),
    MULTIPLAS_RESPOSTAS_FALSAS("multiplas_respostas_falsas"),
    VERDADEIRA_OU_FALSA("verdadeira_ou_falsa");

    private final String descricao;

    TipoQuestao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static TipoQuestao fromDescricao(String descricao) {
        for (TipoQuestao tipo : TipoQuestao.values()) {
            if (tipo.descricao.equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de questão inválida: " + descricao);
    }
}
