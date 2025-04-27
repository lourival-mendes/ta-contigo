package br.com.tacontigo.entities;

import java.time.LocalDateTime;

import br.com.tacontigo.enums.TipoMidia;

public record Midia(
        String descricao,
        TipoMidia tipo,
        LocalDateTime dataRegistro,
        String url) {
}