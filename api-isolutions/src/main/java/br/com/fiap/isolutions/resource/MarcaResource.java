package br.com.fiap.isolutions.resource;

import br.com.fiap.isolutions.dao.MarcaDao;
import br.com.fiap.isolutions.model.Marca;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/marcas")
public class MarcaResource {

    private MarcaDao marcaDao = new MarcaDao();

    @GET
    public Response buscarMarcas() {
        try {
            List<Marca> marcas = marcaDao.buscarTodasMarcas();
            if (marcas.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhuma marca encontrada.")
                        .build();
            }
            return Response.ok(marcas).build();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar marcas: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Erro ao buscar marcas.")
                    .build();
        }
    }
}
