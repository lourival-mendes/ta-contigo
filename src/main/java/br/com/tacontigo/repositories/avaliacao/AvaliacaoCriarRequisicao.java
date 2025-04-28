package br.com.tacontigo.repositories.avaliacao;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import br.com.tacontigo.entities.Questao;
import br.com.tacontigo.enums.Assunto;

public record AvaliacaoCriarRequisicao(
                String nome,
                String descricao,
                LocalDateTime inicio,
                LocalDateTime termino,
                int tempoLimite,
                int tentativasMaximas,
                ObjectId usuarioId,
                List<Questao> questoes,
                Assunto assunto) {
}
