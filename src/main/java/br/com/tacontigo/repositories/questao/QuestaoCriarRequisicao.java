package br.com.tacontigo.repositories.questao;

import java.util.List;

import br.com.tacontigo.entities.Alternativa;
import br.com.tacontigo.entities.Midia;
import br.com.tacontigo.enums.TipoQuestao;

public record QuestaoCriarRequisicao(
                String conteudo,
                TipoQuestao tipo,
                List<Midia> midia,
                List<Alternativa> alternativa,
                float peso,
                int ordem) {
}
