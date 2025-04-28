package br.com.tacontigo.resources;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import br.com.tacontigo.repositories.avaliacao.AvaliacaoAtualizarRequisicao;
import br.com.tacontigo.repositories.avaliacao.AvaliacaoCriarRequisicao;
import br.com.tacontigo.repositories.avaliacao.AvaliacaoResposta;
import br.com.tacontigo.services.AvaliacaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/avaliacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoResource {

    private final AvaliacaoService questaoService;

    @Inject
    public AvaliacaoResource(AvaliacaoService questaoService) {
        this.questaoService = questaoService;
    }

    @POST
    public Response cria(@RequestBody AvaliacaoCriarRequisicao questaoCriarRequisicao) {
        questaoService.criaAvaliacao(questaoCriarRequisicao);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<AvaliacaoResposta> lista() {
        return questaoService.listaAvaliacoes();
    }

    @GET
    @Path("{id}")
    public AvaliacaoResposta busca(String id) {
        return questaoService.buscaAvaliacao(id);
    }

    @POST
    @Path("{id}")
    public void atualiza(AvaliacaoAtualizarRequisicao questaoAtualizarRequisicao, String id) {
        questaoService.atualizaAvaliacao(questaoAtualizarRequisicao, id);
    }

    @DELETE
    @Path("{id}")
    public void deleta(String id) {
        questaoService.deletaAvaliacao(id);
    }
}
