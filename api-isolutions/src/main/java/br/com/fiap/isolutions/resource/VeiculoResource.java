package br.com.fiap.isolutions.resource;

import br.com.fiap.isolutions.dao.VeiculoDao;
import br.com.fiap.isolutions.model.Veiculo;
import br.com.fiap.isolutions.util.ErrorResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/veiculo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoResource {

    private VeiculoDao veiculoDao = new VeiculoDao();

    @POST
    public Response criarVeiculo(Veiculo veiculo, @Context UriInfo uriInfo) {
        try {
            veiculoDao.inserirVeiculo(veiculo);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.path(Integer.toString(veiculo.getIdVeiculo())).build()).entity(veiculo).build();
        } catch (SQLException e) {
            return ErrorResponse.createErrorResponse(Response.Status.BAD_REQUEST, e.getMessage());
        }
    }


    @GET
    @Path("/{idCliente}")
    public Response buscarVeiculosPorIdCliente(@PathParam("idCliente") int idCliente) {
        try {
            List<Veiculo> veiculos = veiculoDao.buscarVeiculosPorIdCliente(idCliente);
            if (veiculos.isEmpty()) {
                return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Nenhum veículo encontrado.");
            }
            return Response.ok(veiculos).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return ErrorResponse.createErrorResponse(Response.Status.INTERNAL_SERVER_ERROR, "Erro ao buscar veículos.");
        }
    }


    @GET
    public Response BuscarTodosVeiculos() {
        try {
            List<Veiculo> veiculos = veiculoDao.listarVeiculos();
            if (veiculos.isEmpty()) {
                return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Nenhum veículo encontrado.");
            }
            return Response.ok(veiculos).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Erro ao listar veículos.");
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarVeiculo(@PathParam("id") int id, Veiculo veiculoAtualizado) {
        try {
            Veiculo veiculo = veiculoDao.buscarVeiculoPorId(id);
            if (veiculo == null) {
                return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Veículo não encontrado.");
            }

            veiculo.setModelo(veiculoAtualizado.getModelo());
            veiculo.setAno(veiculoAtualizado.getAno());
            veiculo.setPlaca(veiculoAtualizado.getPlaca());
            veiculo.setIdMarca(veiculoAtualizado.getIdMarca());
            veiculo.setIdCliente(veiculoAtualizado.getIdCliente());

            veiculoDao.atualizarVeiculo(veiculo);
            return Response.ok(veiculo).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Erro ao atualizar veículo.");
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeVeiculo(@PathParam("id") int id) {
        try {
            Veiculo veiculo = veiculoDao.buscarVeiculoPorId(id);
            if (veiculo == null) {
                return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Veículo não encontrado.");
            }
                veiculoDao.removerVeiculo(id);
                return Response.status(Response.Status.OK).entity("Veículo removido com sucesso.").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Erro ao remover veículo.");
        }
    }
}
