package br.com.fiap.isolutions.resource;

import br.com.fiap.isolutions.dao.ClienteDao;
import br.com.fiap.isolutions.model.Cliente;
import br.com.fiap.isolutions.util.ErrorResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.util.List;

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private ClienteDao clienteDao = new ClienteDao();

    @POST
    public Response criarCliente(Cliente cliente, @Context UriInfo uriInfo) {
        try {
            clienteDao.inserirCliente(cliente);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.path(Integer.toString(cliente.getIdCliente())).build()).entity(cliente).build();
        } catch (SQLException e) {
            return ErrorResponse.createErrorResponse(Response.Status.BAD_REQUEST, e.getMessage());
        }
    }

    @GET
    @Path("/{cpf}")
    public Response buscarClientePorCpf(@PathParam("cpf") String cpf) {
        try {
            Cliente cliente = clienteDao.buscarClientePorCpf(cpf);
            if (cliente == null) {
                return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Cliente não encontrado");
            }
            return Response.ok(cliente).build();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
            return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Erro ao buscar cliente");
        }
    }

    @GET
    @Path("/login")
    public Response loginCliente(@QueryParam("cpf") String cpf, @QueryParam("senha") String senha) {
        try {
            Cliente cliente = clienteDao.buscarClientePorLogin(cpf, senha);
            return Response.ok(cliente).build();
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.equals("CPF não encontrado")) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\": \"CPF não encontrado\"}")
                        .build();
            } else if (errorMessage.equals("Senha incorreta")) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"message\": \"Senha incorreta\"}")
                        .build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\": \"Erro no servidor\"}")
                    .build();
        }
    }



    @GET
    public Response buscarTodosClientes() {
        try {
            List<Cliente> clientes = clienteDao.listar();
            if (clientes.isEmpty()) {
                return ErrorResponse.createErrorResponse(Response.Status.NO_CONTENT, "Nenhum cliente encontrado");
            }
            return Response.ok(clientes).build();
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
            return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Erro ao listar clientes");
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCliente(@PathParam("id") int id, Cliente clienteAtualizado) {
        try {
            Cliente cliente = clienteDao.buscarClientePorId(id);
            if (cliente == null) {
                return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Cliente não encontrado para atualização");
            }
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setCpf(clienteAtualizado.getCpf());
            cliente.setLogin(clienteAtualizado.getLogin());
            cliente.setSenha(clienteAtualizado.getSenha());

            clienteDao.atualizar(cliente);
            return Response.ok(cliente).build();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
            return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Erro ao atualizar cliente");
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeCliente(@PathParam("id") int id) {
        try {
            Cliente cliente = clienteDao.buscarClientePorId(id);
            if (cliente == null) {
                return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Cliente não encontrado");
            }
            clienteDao.remover(id);
            return Response.status(Response.Status.OK).entity("Cliente removido com sucesso.").build();
        } catch (SQLException e) {
            System.err.println("Erro ao remover cliente: " + e.getMessage());
            return ErrorResponse.createErrorResponse(Response.Status.NOT_FOUND, "Erro ao remover cliente");
        }
    }
}
