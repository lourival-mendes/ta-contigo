package br.com.tacontigo.services;

import java.time.LocalDateTime;
import java.util.List;

import br.com.tacontigo.entities.Usuario;
import br.com.tacontigo.enums.Genero;
import br.com.tacontigo.enums.Raca;
import br.com.tacontigo.enums.TipoUsuario;
import br.com.tacontigo.exceptions.UsuarioException;
import br.com.tacontigo.repositories.usuario.UsuarioAtualizarRequisicao;
import br.com.tacontigo.repositories.usuario.UsuarioCriarRequisicao;
import br.com.tacontigo.repositories.usuario.UsuarioResposta;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioService {

    private <T> void validaUsuario(T usuario) {
        if (usuario instanceof UsuarioCriarRequisicao usuarioCriar) {
            validaUsuarioCriar(usuarioCriar);
        } else if (usuario instanceof UsuarioAtualizarRequisicao usuarioAtualizar) {
            validaUsuarioAtualizar(usuarioAtualizar);
        } else {
            throw new IllegalArgumentException("Tipo de usuário não suportado.");
        }
    }

    private void validaUsuarioCriar(UsuarioCriarRequisicao usuarioCriar) {
        if (usuarioCriar.nomeCompleto() == null || usuarioCriar.nomeCompleto().isEmpty()) {
            throw new UsuarioException("Nome completo não pode ser vazio.", "USR-001");
        }
        if (usuarioCriar.genero() == null) {
            throw new UsuarioException("Gênero não pode ser vazio.", "USR-002");
        }
        try {
            Genero.valueOf(usuarioCriar.genero().name());
        } catch (Exception e) {
            throw new UsuarioException("Gênero inválido: " + usuarioCriar.genero(), "USR-003");
        }
        if (usuarioCriar.raca() == null) {
            throw new UsuarioException("Raça não pode ser vazia.", "USR-004");
        }
        try {
            Raca.valueOf(usuarioCriar.raca().name());
        } catch (Exception e) {
            throw new UsuarioException("Raça inválida: " + usuarioCriar.raca(), "USR-005");
        }
        if (usuarioCriar.nacionalidade() == null || usuarioCriar.nacionalidade().isEmpty()) {
            throw new UsuarioException("Nacionalidade não pode ser vazia.", "USR-006");
        }
    }

    private void validaUsuarioAtualizar(UsuarioAtualizarRequisicao usuarioAtualizar) {
        if (usuarioAtualizar.nomeCompleto() == null || usuarioAtualizar.nomeCompleto().isEmpty()) {
            throw new UsuarioException("Nome completo não pode ser nulo ou vazio.", "USR-001");
        }
        if (usuarioAtualizar.tipoUsuario() == null) {
            throw new UsuarioException("Tipo usuário não pode ser nulo.", "USR-007");
        }
        try {
            TipoUsuario.valueOf(usuarioAtualizar.tipoUsuario().name());
        } catch (IllegalArgumentException e) {
            throw new UsuarioException("Tipo usuário inválido: " + usuarioAtualizar.tipoUsuario(), "USR-008");
        }
        if (usuarioAtualizar.genero() == null) {
            throw new UsuarioException("Gênero não pode ser vazio.", "USR-002");
        }
        try {
            Genero.valueOf(usuarioAtualizar.genero().name());
        } catch (IllegalArgumentException e) {
            throw new UsuarioException("Gênero inválido: " + usuarioAtualizar.genero(), "USR-003");
        }
        if (usuarioAtualizar.raca() == null) {
            throw new UsuarioException("Raça não pode ser vazia.", "USR-004");
        }
        try {
            Raca.valueOf(usuarioAtualizar.raca().name());
        } catch (IllegalArgumentException e) {
            throw new UsuarioException("Raça inválida: " + usuarioAtualizar.raca(), "USR-005");
        }
        if (usuarioAtualizar.nacionalidade() == null || usuarioAtualizar.nacionalidade().isEmpty()) {
            throw new UsuarioException("Nacionalidade não pode ser vazia.", "USR-006");
        }
    }

    private void verificaUsuarioExistente(String nomeCompleto) {
        Usuario usuarioExistente = Usuario.buscaUsuarioNomeCompleto(nomeCompleto);
        if (usuarioExistente != null) {
            throw new UsuarioException("Usuário já existe com o nome: " + nomeCompleto, "USR-09");
        }
    }

    public void criaUsuario(UsuarioCriarRequisicao usuarioCriar) {

        validaUsuario(usuarioCriar);
        verificaUsuarioExistente(usuarioCriar.nomeCompleto());

        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(usuarioCriar.nomeCompleto());
        usuario.setGenero(usuarioCriar.genero());
        usuario.setRaca(usuarioCriar.raca());
        usuario.setNacionalidade(usuarioCriar.nacionalidade());
        usuario.setTipoUsuario(TipoUsuario.VISITANTE);
        usuario.setDataRegistro(LocalDateTime.now());
        try {
            usuario.persist();
        } catch (Exception e) {
            throw new UsuarioException("Erro ao criar usuário: " + e.getMessage(), "GEN-010");
        }
    }

    public void atualizaUsuario(UsuarioAtualizarRequisicao usuarioAtualizar, String id) {

        validaUsuario(usuarioAtualizar);
        Usuario usuario = Usuario.findById(id);

        if (usuario == null) {
            throw new UsuarioException("Usuário não encontrado com o ID: " + id, "GEN-011");
        }

        usuario.setNomeCompleto(usuarioAtualizar.nomeCompleto());
        usuario.setTipoUsuario(usuarioAtualizar.tipoUsuario());
        usuario.setGenero(usuarioAtualizar.genero());
        usuario.setRaca(usuarioAtualizar.raca());
        usuario.setNacionalidade(usuarioAtualizar.nacionalidade());
        usuario.update();
    }

    public UsuarioResposta buscaUsuario(String id) {
        Usuario usuario = Usuario.findById(id);

        if (usuario == null) {
            throw new UsuarioException("Usuário não encontrado com o ID: " + id, "GEN-011");
        } else {
            return UsuarioResposta.converteUsuario(usuario);
        }

    }

    public List<UsuarioResposta> listaUsuarios() {

        List<UsuarioResposta> usuarios = Usuario
                .listAll()
                .stream()
                .map(UsuarioResposta::converteUsuario)
                .toList();

        if (usuarios.isEmpty()) {
            throw new UsuarioException("Nenhum não encontrado", "GEN-012");
        } else {
            return usuarios;
        }

    }

    public void deletaUsuario(String id) {
        Usuario usuario = Usuario.findById(id);

        if (usuario == null) {
            throw new UsuarioException("Usuário não encontrado com o ID: " + id, "GEN-011");
        } else {
            usuario.delete();
        }
    }

}
