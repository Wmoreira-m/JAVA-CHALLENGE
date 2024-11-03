package br.com.fiap.isolutions.dao;

import br.com.fiap.isolutions.conection.ConexaoBanco;
import br.com.fiap.isolutions.model.Marca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaDao {

    public List<Marca> buscarTodasMarcas() throws SQLException {
        String sql = "SELECT * FROM T_ISL_MARCA";
        List<Marca> marcas = new ArrayList<>();

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Marca marca = new Marca(
                        rs.getInt("ID_MARCA"),
                        rs.getString("NOME")
                );
                marcas.add(marca);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar marcas: " + e.getMessage());
            throw new SQLException("Erro ao buscar marcas.", e);
        }
        return marcas;
    }
}
