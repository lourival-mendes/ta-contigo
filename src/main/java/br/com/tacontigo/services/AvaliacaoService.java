package br.com.tacontigo.services;

import java.util.List;

import br.com.tacontigo.entities.Avaliacao;
import br.com.tacontigo.enums.Assunto;
import br.com.tacontigo.exceptions.AvaliacaoException;
import br.com.tacontigo.repositories.avaliacao.AvaliacaoAtualizarRequisicao;
import br.com.tacontigo.repositories.avaliacao.AvaliacaoCriarRequisicao;
import br.com.tacontigo.repositories.avaliacao.AvaliacaoResposta;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvaliacaoService {

    private <T> void validaAvaliacao(T avaliacao) {
        if (avaliacao instanceof AvaliacaoCriarRequisicao avaliacaoCriar) {
            validaAvaliacaoCriar(avaliacaoCriar);
        } else if (avaliacao instanceof AvaliacaoAtualizarRequisicao avaliacaoAtualizar) {
            validaAvaliacaoAtualizar(avaliacaoAtualizar);
        } else {
            throw new AvaliacaoException("Tipo de avaliacao não suportado.");
        }
    }

    private void validaAvaliacaoCriar(AvaliacaoCriarRequisicao avaliacaoCriar) {
        if (avaliacaoCriar.nome() == null || avaliacaoCriar.nome().isEmpty()) {
            throw new AvaliacaoException("Nome não pode ser vazio.", "AVA-001");
        }
        if (avaliacaoCriar.assunto() == null || avaliacaoCriar.assunto().name().isEmpty()) {
            throw new AvaliacaoException("Assunto não pode ser vazio.", "AVA-002");
        }
        try {
            Assunto.valueOf(avaliacaoCriar.assunto().name());
        } catch (Exception e) {
            throw new AvaliacaoException("Assunto inválido: " + avaliacaoCriar.assunto().name(), "AVA-003");
        }
        if (avaliacaoCriar.descricao() == null || avaliacaoCriar.descricao().isEmpty()) {
            throw new AvaliacaoException("Descrição não pode ser vazia.", "AVA-006");
        }
        if (avaliacaoCriar.inicio() == null) {
            throw new AvaliacaoException("Data início não pode ser vazio.", "AVA-007");
        }
        if (avaliacaoCriar.termino() == null) {
            throw new AvaliacaoException("Data término não pode ser vazio.", "AVA-007");
        }
        if (avaliacaoCriar.questoes() == null || avaliacaoCriar.questoes().isEmpty()) {
            throw new AvaliacaoException("Questões não pode ser vazio.", "AVA-007");
        }
        if (avaliacaoCriar.usuarioId() == null) {
            throw new AvaliacaoException("Questões não pode ser vazio.", "AVA-007");
        }

    }

    private void validaAvaliacaoAtualizar(AvaliacaoAtualizarRequisicao avaliacaoAtualizar) {
        if (avaliacaoAtualizar.nome() == null || avaliacaoAtualizar.nome().isEmpty()) {
            throw new AvaliacaoException("Nome não pode ser vazio.", "AVA-001");
        }
        if (avaliacaoAtualizar.assunto() == null || avaliacaoAtualizar.assunto().name().isEmpty()) {
            throw new AvaliacaoException("Assunto não pode ser vazio.", "AVA-002");
        }
        try {
            Assunto.valueOf(avaliacaoAtualizar.assunto().name());
        } catch (Exception e) {
            throw new AvaliacaoException("Assunto inválido: " + avaliacaoAtualizar.assunto().name(), "AVA-003");
        }
        if (avaliacaoAtualizar.descricao() == null || avaliacaoAtualizar.descricao().isEmpty()) {
            throw new AvaliacaoException("Descrição não pode ser vazia.", "AVA-006");
        }
        if (avaliacaoAtualizar.inicio() == null) {
            throw new AvaliacaoException("Data início não pode ser vazio.", "AVA-007");
        }
        if (avaliacaoAtualizar.termino() == null) {
            throw new AvaliacaoException("Data término não pode ser vazio.", "AVA-007");
        }
        if (avaliacaoAtualizar.questoes() == null || avaliacaoAtualizar.questoes().isEmpty()) {
            throw new AvaliacaoException("Questões não pode ser vazio.", "AVA-007");
        }
        if (avaliacaoAtualizar.usuarioId() == null) {
            throw new AvaliacaoException("Questões não pode ser vazio.", "AVA-007");
        }

    }

    public void criaAvaliacao(AvaliacaoCriarRequisicao avaliacaoCriar) {

        validaAvaliacao(avaliacaoCriar);
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setAssunto(avaliacaoCriar.assunto());
        avaliacao.setAtivo(true);
        avaliacao.setDescricao(avaliacaoCriar.descricao());
        avaliacao.setInicio(avaliacaoCriar.inicio());
        avaliacao.setNome(avaliacaoCriar.nome());
        avaliacao.setQuestoes(avaliacaoCriar.questoes());
        avaliacao.setTempoLimite(avaliacaoCriar.tempoLimite());
        avaliacao.setTentativasMaximas(avaliacaoCriar.tentativasMaximas());
        avaliacao.setTermino(avaliacaoCriar.termino());
        avaliacao.setUsuarioId(avaliacaoCriar.usuarioId());
        try {
            avaliacao.persist();
        } catch (Exception e) {
            throw new AvaliacaoException("Erro ao criar avaliação: " + e.getMessage(), "GEN-010");
        }
    }

    public void atualizaAvaliacao(AvaliacaoAtualizarRequisicao avaliacaoAtualizar, String id) {

        validaAvaliacao(avaliacaoAtualizar);
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setAssunto(avaliacaoAtualizar.assunto());
        avaliacao.setAtivo(avaliacaoAtualizar.ativo());
        avaliacao.setDescricao(avaliacaoAtualizar.descricao());
        avaliacao.setInicio(avaliacaoAtualizar.inicio());
        avaliacao.setNome(avaliacaoAtualizar.nome());
        avaliacao.setQuestoes(avaliacaoAtualizar.questoes());
        avaliacao.setTempoLimite(avaliacaoAtualizar.tempoLimite());
        avaliacao.setTentativasMaximas(avaliacaoAtualizar.tentativasMaximas());
        avaliacao.setTermino(avaliacaoAtualizar.termino());
        avaliacao.setUsuarioId(avaliacaoAtualizar.usuarioId());
        try {
            avaliacao.update();
        } catch (Exception e) {
            throw new AvaliacaoException("Erro ao atualizar avaliação: " + e.getMessage(), "GEN-010");
        }
    }

    public AvaliacaoResposta buscaAvaliacao(String id) {
        Avaliacao avaliacao = Avaliacao.findById(id);
        return AvaliacaoResposta.converteAvaliacao(avaliacao);
    }

    public List<AvaliacaoResposta> listaAvaliacoes() {

        List<AvaliacaoResposta> questoes = Avaliacao
                .listAll()
                .stream()
                .map(AvaliacaoResposta::converteAvaliacao)
                .toList();

        if (questoes.isEmpty()) {
            throw new AvaliacaoException("Nenhuma avaliação encontrada", "GEN-012");
        } else {
            return questoes;
        }

    }

    public void deletaAvaliacao(String id) {
        Avaliacao avaliacao = Avaliacao.findById(id);
        avaliacao.delete();
    }

}
