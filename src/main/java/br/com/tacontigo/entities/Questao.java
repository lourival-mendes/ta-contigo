package br.com.tacontigo.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import br.com.tacontigo.enums.Assunto;
import br.com.tacontigo.enums.TipoQuestao;
import br.com.tacontigo.exceptions.QuestaoException;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MongoEntity(collection = "questoes")
public class Questao extends PanacheMongoEntity {

    private String conteudo;
    private TipoQuestao tipo;
    private LocalDateTime data;
    private List<Midia> midia;
    private List<Alternativa> alternativa;
    private float peso;
    private int ordem;
    private Assunto assunto;

    public ObjectId getId() {
        return id;
    }

    public static Questao findById(String id) {
        return findByIdOptional(new ObjectId(id))
                .map(Questao.class::cast)
                .orElseThrow(() -> new QuestaoException("Questão não encontrada com o ID: " + id, "QUE-004"));
    }

    @SuppressWarnings("unchecked")
    public static List<Questao> listAll() {
        return findAll().list();
    }

}