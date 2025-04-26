package br.com.tacontigo.enums;

public enum TipoUsuario {
    ADMINISTRADOR("Administrador"),
    USUARIO("Usuário"),
    VISITANTE("Visitante");

    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static TipoUsuario fromDescricao(String descricao) {
        for (TipoUsuario tipo : TipoUsuario.values()) {
            if (tipo.descricao.equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de usuário inválido: " + descricao);
    }
}
