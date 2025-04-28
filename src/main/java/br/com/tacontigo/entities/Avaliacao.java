package br.com.tacontigo.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import br.com.tacontigo.enums.Assunto;
import br.com.tacontigo.exceptions.AvaliacaoException;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MongoEntity(collection = "avaliacoes")
public class Avaliacao extends PanacheMongoEntity {

    private String nome;
    private String descricao;
    private LocalDateTime inicio;
    private LocalDateTime termino;
    private int tempoLimite;
    private int tentativasMaximas;
    private ObjectId usuarioId;
    private List<Questao> questoes;
    private Assunto assunto;
    private boolean ativo;

    public ObjectId getId() {
        return id;
    }

    public static Avaliacao filtraAvaliacao(Avaliacao avaliacao) {
        return find("id", avaliacao.id).firstResult();
    }

    public static Avaliacao findById(String id) {
        return findByIdOptional(new ObjectId(id))
                .map(Avaliacao.class::cast)
                .orElseThrow(() -> new AvaliacaoException("Avaliação não encontrada com o id: " + id, "AVA-001"));
    }

    @SuppressWarnings("unchecked")
    public static List<Avaliacao> listAll() {
        return findAll().list();
    }

}
