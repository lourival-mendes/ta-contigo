package br.com.tacontigo.resources;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import br.com.tacontigo.repositories.usuario.UsuarioAtualizarRequisicao;
import br.com.tacontigo.repositories.usuario.UsuarioCriarRequisicao;
import br.com.tacontigo.repositories.usuario.UsuarioResposta;
import br.com.tacontigo.services.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private final UsuarioService usuarioService;

    @Inject
    public UsuarioResource(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @POST
    public Response cria(@RequestBody UsuarioCriarRequisicao usuarioCriarRequisicao) {
        usuarioService.criaUsuario(usuarioCriarRequisicao);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<UsuarioResposta> lista() {
        return usuarioService.listaUsuarios();
    }

    @GET
    @Path("/users/{id}")
    public UsuarioResposta busca(String id) {
        return usuarioService.buscaUsuario(id);
    }

    @POST
    @Path("/users/{id}")
    public void atualiza(UsuarioAtualizarRequisicao usuarioAtualizarRequisicao, String id) {
        usuarioService.atualizaUsuario(usuarioAtualizarRequisicao, id);
    }

    @DELETE
    @Path("/users/{id}")
    public void deleta(String id) {
        usuarioService.deletaUsuario(id);
    }
}
