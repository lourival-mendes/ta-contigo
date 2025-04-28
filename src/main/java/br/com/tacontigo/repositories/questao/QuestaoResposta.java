package br.com.tacontigo.repositories.questao;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import br.com.tacontigo.entities.Alternativa;
import br.com.tacontigo.entities.Midia;
import br.com.tacontigo.entities.Questao;
import br.com.tacontigo.enums.Assunto;
import br.com.tacontigo.enums.TipoQuestao;

public record QuestaoResposta(
                ObjectId id,
                String conteudo,
                TipoQuestao tipo,
                LocalDateTime data,
                List<Midia> midia,
                List<Alternativa> alternativa,
                float peso,
                int ordem,
                Assunto assunto) {

        public static QuestaoResposta converteQuestao(Questao questao) {
                return new QuestaoResposta(
                                questao.getId(),
                                questao.getConteudo(),
                                questao.getTipo(),
                                questao.getData(),
                                questao.getMidia(),
                                questao.getAlternativa(),
                                questao.getPeso(),
                                questao.getOrdem(),
                                questao.getAssunto());
        }
}
