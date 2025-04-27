package br.com.tacontigo.repositories.usuario;

import java.time.LocalDateTime;

import br.com.tacontigo.enums.Genero;
import br.com.tacontigo.enums.Raca;
import br.com.tacontigo.enums.TipoUsuario;

public record UsuarioAtualizarRequisicao(String nomeCompleto, TipoUsuario tipoUsuario, LocalDateTime dataRegistro, Genero genero,
        Raca raca, String nacionalidade) {
    public UsuarioAtualizarRequisicao {

        if (nomeCompleto == null || nomeCompleto.isBlank()) {
            throw new IllegalArgumentException("Nome completo não pode ser vazio.");
        }
        if (tipoUsuario == null || tipoUsuario.name().isBlank()) {
            throw new IllegalArgumentException("Tipo de usuário não pode ser vazio.");
        }
        if (dataRegistro == null) {
            throw new IllegalArgumentException("Data de registro não pode ser vazia.");
        }
        if (genero == null || genero.name().isBlank()) {
            throw new IllegalArgumentException("Gênero não pode ser vazio.");
        }
        if (raca == null || raca.name().isBlank()) {
            throw new IllegalArgumentException("Raça não pode ser vazia.");
        }
        if (nacionalidade == null || nacionalidade.isBlank()) {
            throw new IllegalArgumentException("Nacionalidade não pode ser vazia.");
        }
    }
}
