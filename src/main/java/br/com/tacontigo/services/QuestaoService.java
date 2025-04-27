package br.com.tacontigo.services;

import java.time.LocalDateTime;
import java.util.List;

import br.com.tacontigo.entities.Questao;
import br.com.tacontigo.enums.TipoQuestao;
import br.com.tacontigo.exceptions.QuestaoException;
import br.com.tacontigo.repositories.questao.QuestaoAtualizarRequisicao;
import br.com.tacontigo.repositories.questao.QuestaoCriarRequisicao;
import br.com.tacontigo.repositories.questao.QuestaoResposta;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuestaoService {

    private <T> void validaQuestao(T questao) {
        if (questao instanceof QuestaoCriarRequisicao questaoCriar) {
            validaQuestaoCriar(questaoCriar);
        } else if (questao instanceof QuestaoAtualizarRequisicao questaoAtualizar) {
            validaQuestaoAtualizar(questaoAtualizar);
        } else {
            throw new QuestaoException("Tipo de questao não suportado.");
        }
    }

    private void validaQuestaoCriar(QuestaoCriarRequisicao questaoCriar) {
        if (questaoCriar.conteudo() == null || questaoCriar.conteudo().isEmpty()) {
            throw new QuestaoException("Conteudo não pode ser vazio.", "QUE-001");
        }
        if (questaoCriar.tipo() == null) {
            throw new QuestaoException("Tipo não pode ser vazio.", "QUE-002");
        }
        try {
            TipoQuestao.valueOf(questaoCriar.tipo().name());
        } catch (Exception e) {
            throw new QuestaoException("Tipo inválido: " + questaoCriar.tipo().name(), "QUE-003");
        }
        if (questaoCriar.alternativa() == null || questaoCriar.alternativa().size() == 0) {
            throw new QuestaoException("Alternativa não pode ser vazia.", "QUE-006");
        }
    }

    private void validaQuestaoAtualizar(QuestaoAtualizarRequisicao questaoAtualizar) {
        if (questaoAtualizar.conteudo() == null || questaoAtualizar.conteudo().isEmpty()) {
            throw new QuestaoException("Conteudo não pode ser vazio.", "QUE-001");
        }
        if (questaoAtualizar.tipo() == null) {
            throw new QuestaoException("Tipo não pode ser vazio.", "QUE-002");
        }
        try {
            TipoQuestao.valueOf(questaoAtualizar.tipo().name());
        } catch (Exception e) {
            throw new QuestaoException("Tipo inválido: " + questaoAtualizar.tipo(), "QUE-003");
        }
        if (questaoAtualizar.alternativa() == null || questaoAtualizar.alternativa().size() == 0) {
            throw new QuestaoException("Alternativa não pode ser vazia.", "QUE-006");
        }
    }

    public void criaQuestao(QuestaoCriarRequisicao questaoCriar) {

        validaQuestao(questaoCriar);
        Questao questao = new Questao();
        questao.setConteudo(questaoCriar.conteudo());
        questao.setMidia(questaoCriar.midia());
        questao.setTipo(questaoCriar.tipo());
        questao.setAlternativa(questaoCriar.alternativa());
        questao.setPeso(questaoCriar.peso());
        questao.setOrdem(questaoCriar.ordem());
        questao.setData(LocalDateTime.now());
        try {
            questao.persist();
        } catch (Exception e) {
            throw new QuestaoException("Erro ao criar questão: " + e.getMessage(), "GEN-010");
        }
    }

    public void atualizaQuestao(QuestaoAtualizarRequisicao questaoAtualizar, String id) {

        validaQuestao(questaoAtualizar);
        Questao questao = Questao.findById(id);
        questao.setConteudo(questaoAtualizar.conteudo());
        questao.setMidia(questaoAtualizar.midia());
        questao.setTipo(questaoAtualizar.tipo());
        questao.setAlternativa(questaoAtualizar.alternativa());
        questao.setPeso(questaoAtualizar.peso());
        questao.setOrdem(questaoAtualizar.ordem());
        try {
            questao.update();
        } catch (Exception e) {
            throw new QuestaoException("Erro ao atualizar questão: " + e.getMessage(), "GEN-010");
        }
    }

    public QuestaoResposta buscaQuestao(String id) {
        Questao questao = Questao.findById(id);
        return QuestaoResposta.converteQuestao(questao);
    }

    public List<QuestaoResposta> listaQuestoes() {

        List<QuestaoResposta> questoes = Questao
                .listAll()
                .stream()
                .map(QuestaoResposta::converteQuestao)
                .toList();

        if (questoes.isEmpty()) {
            throw new QuestaoException("Nenhuma questão encontrada", "GEN-012");
        } else {
            return questoes;
        }

    }

    public void deletaQuestao(String id) {
        Questao questao = Questao.findById(id);
        questao.delete();
    }

}
