package br.com.tacontigo.repositories.avaliacao;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import br.com.tacontigo.entities.Avaliacao;
import br.com.tacontigo.entities.Questao;
import br.com.tacontigo.enums.Assunto;

public record AvaliacaoResposta(
                ObjectId id,
                String nome,
                String descricao,
                LocalDateTime inicio,
                LocalDateTime termino,
                int tempoLimite,
                int tentativasMaximas,
                ObjectId usuarioId,
                List<Questao> questoes,
                Assunto assunto,
                boolean ativo) {

        public static AvaliacaoResposta converteAvaliacao(Avaliacao avaliacao) {
                return new AvaliacaoResposta(
                                avaliacao.getId(),
                                avaliacao.getNome(),
                                avaliacao.getDescricao(),
                                avaliacao.getInicio(),
                                avaliacao.getTermino(),
                                avaliacao.getTempoLimite(),
                                avaliacao.getTentativasMaximas(),
                                avaliacao.getUsuarioId(),
                                avaliacao.getQuestoes(),
                                avaliacao.getAssunto(),
                                avaliacao.isAtivo());
        }
}
