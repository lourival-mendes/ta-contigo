package br.com.tacontigo.repositories.usuario;

import br.com.tacontigo.enums.Genero;
import br.com.tacontigo.enums.Raca;

public record UsuarioCriarRequisicao(String nomeCompleto, Genero genero, Raca raca, String nacionalidade) {
}
