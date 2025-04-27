package br.com.tacontigo.repositories.usuario;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import br.com.tacontigo.entities.Usuario;
import br.com.tacontigo.enums.Genero;
import br.com.tacontigo.enums.Raca;
import br.com.tacontigo.enums.TipoUsuario;

public record UsuarioResposta(
                ObjectId id,
                String nomeCompleto,
                TipoUsuario tipoUsuario,
                LocalDateTime dataRegistro,
                Genero genero,
                Raca raca,
                String nacionalidade) {

        public static UsuarioResposta converteUsuario(Usuario usuario) {
                return new UsuarioResposta(
                                usuario.getId(),
                                usuario.getNomeCompleto(),
                                usuario.getTipoUsuario(),
                                usuario.getDataRegistro(),
                                usuario.getGenero(),
                                usuario.getRaca(),
                                usuario.getNacionalidade());
        }
}
