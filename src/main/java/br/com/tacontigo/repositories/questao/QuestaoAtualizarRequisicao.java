package br.com.tacontigo.repositories.questao;

import java.util.List;

import br.com.tacontigo.entities.Alternativa;
import br.com.tacontigo.entities.Midia;
import br.com.tacontigo.enums.Assunto;
import br.com.tacontigo.enums.TipoQuestao;
import br.com.tacontigo.exceptions.QuestaoException;

public record QuestaoAtualizarRequisicao(
        String conteudo,
        TipoQuestao tipo,
        List<Midia> midia,
        List<Alternativa> alternativa,
        float peso,
        int ordem,
        Assunto assunto) {

    public QuestaoAtualizarRequisicao {

        if (assunto == null || assunto.name().isBlank()) {
            throw new QuestaoException("Assunto não pode ser vazio.");
        }
        if (conteudo == null || conteudo.isBlank()) {
            throw new QuestaoException("Conteudo não pode ser vazio.");
        }
        if (tipo == null || tipo.name().isBlank()) {
            throw new QuestaoException("Tipo não pode ser vazio.");
        }
        if (alternativa == null || alternativa.isEmpty()) {
            throw new QuestaoException("Alternativas não pode ser vazio.");
        }
    }
}
