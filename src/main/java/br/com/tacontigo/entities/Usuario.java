package br.com.tacontigo.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import br.com.tacontigo.enums.Genero;
import br.com.tacontigo.enums.Raca;
import br.com.tacontigo.enums.TipoUsuario;
import br.com.tacontigo.exceptions.UsuarioException;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MongoEntity(collection = "usuarios")
public class Usuario extends PanacheMongoEntity {

    private String nomeCompleto;
    private TipoUsuario tipoUsuario;
    private LocalDateTime dataRegistro;
    private Genero genero;
    private Raca raca;
    private String nacionalidade;

    public ObjectId getId() {
        return id;
    }

    public static Usuario buscaUsuarioNomeCompleto(String nomeCompleto) {
        return find("nomeCompleto", nomeCompleto).firstResult();
    }

    public static Usuario findById(String id) {
        return findByIdOptional(new ObjectId(id))
                .map(Usuario.class::cast)
                .orElseThrow(() -> new UsuarioException("Usuário não encontrado com o id: " + id, "USU-001"));
    }

    @SuppressWarnings("unchecked")
    public static List<Usuario> listAll() {
        return findAll().list();
    }

}