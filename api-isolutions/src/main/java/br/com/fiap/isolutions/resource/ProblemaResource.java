package br.com.fiap.isolutions.resource;

import br.com.fiap.isolutions.dao.ProblemaDao;
import br.com.fiap.isolutions.model.Problema;
import br.com.fiap.isolutions.util.ErrorResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.util.List;

@Path("/problema")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProblemaResource {

    private ProblemaDao problemaDao = new ProblemaDao();

    @POST
    public Response criarProblema(Problema problema, @Context UriInfo uriInfo) {
        try {
            problemaDao.inserirProblema(problema);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.path(Integer.toString(problema.getIdProblema())).build()).entity(problema).build();
        } catch (SQLException e) {
            return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, e.getMessage());
        }
    }

    @GET
    @Path("/{idVeiculo}")
    public Response buscarProblemasPorIdVeiculo(@PathParam("idVeiculo") int idVeiculo) {
        try {
            List<Problema> problemas = problemaDao.buscarProblemasPorIdVeiculo(idVeiculo);
            if (problemas.isEmpty()) {
                return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Nenhum problema encontrado.");
            }
            return Response.ok(problemas).build();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar problemas: " + e.getMessage());
            return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Erro ao buscar problemas.");
        }
    }

    @GET
    public Response listarProblemas() {
        try {
            List<Problema> problemas = problemaDao.listarProblemas();
            if (problemas.isEmpty()) {
                return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Nenhum problema encontrado.");
            }
            return Response.ok(problemas).build();
        } catch (SQLException e) {
            System.err.println("Erro ao listar problemas: " + e.getMessage());
            return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Erro ao listar problemas.");
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarProblema(@PathParam("id") int id, Problema problemaAtualizado) {
        try {
            Problema problema = problemaDao.buscarProblemaPorId(id);
            if (problema == null) {
                return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Problema não encontrado.");
            }

            problema.setDescricao(problemaAtualizado.getDescricao());
            problema.setTipo(problemaAtualizado.getTipo());
            problema.setGravidade(problemaAtualizado.getGravidade());
            problema.setIdVeiculo(problemaAtualizado.getIdVeiculo());

            problemaDao.atualizarProblema(problema);
            return Response.ok(problema).build();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar problema: " + e.getMessage());
            return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Erro ao atualizar problema.");
        }
    }
}
