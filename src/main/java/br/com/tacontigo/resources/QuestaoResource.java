package br.com.tacontigo.resources;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import br.com.tacontigo.repositories.questao.QuestaoAtualizarRequisicao;
import br.com.tacontigo.repositories.questao.QuestaoCriarRequisicao;
import br.com.tacontigo.repositories.questao.QuestaoResposta;
import br.com.tacontigo.services.QuestaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/questoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestaoResource {

    private final QuestaoService questaoService;

    @Inject
    public QuestaoResource(QuestaoService questaoService) {
        this.questaoService = questaoService;
    }

    @POST
    public Response cria(@RequestBody QuestaoCriarRequisicao questaoCriarRequisicao) {
        questaoService.criaQuestao(questaoCriarRequisicao);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<QuestaoResposta> lista() {
        return questaoService.listaQuestoes();
    }

    @GET
    @Path("/users/{id}")
    public QuestaoResposta busca(String id) {
        return questaoService.buscaQuestao(id);
    }

    @POST
    @Path("/users/{id}")
    public void atualiza(QuestaoAtualizarRequisicao questaoAtualizarRequisicao, String id) {
        questaoService.atualizaQuestao(questaoAtualizarRequisicao, id);
    }

    @DELETE
    @Path("/users/{id}")
    public void deleta(String id) {
        questaoService.deletaQuestao(id);
    }
}
