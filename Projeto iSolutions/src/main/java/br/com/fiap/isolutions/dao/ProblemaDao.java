package br.com.fiap.isolutions.dao;

import br.com.fiap.isolutions.conection.ConexaoBanco;
import br.com.fiap.isolutions.model.Problema;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemaDao {

    public void inserirProblema(Problema problema) throws SQLException {
        String sql = "INSERT INTO T_ISL_PROBLEMA (DESCRICAO, TIPO, GRAVIDADE, ID_VEICULO) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, problema.getDescricao());
            stmt.setString(2, problema.getTipo());
            stmt.setInt(3, problema.getGravidade());
            stmt.setInt(4, problema.getIdVeiculo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            String errorMessage = "Erro ao inserir problema: ";
            if (e.getErrorCode() == 1) {
                errorMessage += "Já existe um problema com essa descrição e veículo";
            } else {
                errorMessage += e.getMessage();
            }
            System.err.println(errorMessage);
            throw new SQLException(errorMessage, e);
        }
    }

    public Problema buscarProblemaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM T_ISL_PROBLEMA WHERE ID_PROBLEMA = ?";
        Problema problema = null;

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                problema = new Problema(
                        rs.getInt("ID_PROBLEMA"),
                        rs.getString("DESCRICAO"),
                        rs.getString("TIPO"),
                        rs.getInt("GRAVIDADE"),
                        rs.getInt("ID_VEICULO"));
            } else {
                System.err.println("Problema com ID " + id + " não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar problema por ID: " + e.getMessage());
            throw new SQLException("Erro ao buscar problema.", e);
        }
        return problema;
    }

    public List<Problema> listarProblemas() throws SQLException {
        List<Problema> problemas = new ArrayList<>();
        String sql = "SELECT * FROM T_ISL_PROBLEMA";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Problema problema = new Problema(
                        rs.getInt("ID_PROBLEMA"),
                        rs.getString("DESCRICAO"),
                        rs.getString("TIPO"),
                        rs.getInt("GRAVIDADE"),
                        rs.getInt("ID_VEICULO"));
                problemas.add(problema);
            }

            if (problemas.isEmpty()) {
                System.out.println("Nenhum problema encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar problemas: " + e.getMessage());
            throw new SQLException("Erro ao listar problemas.", e);
        }
        return problemas;
    }

    public void atualizarProblema(Problema problema) throws SQLException {
        String sql = "UPDATE T_ISL_PROBLEMA SET DESCRICAO = ?, TIPO = ?, GRAVIDADE = ? WHERE ID_PROBLEMA = ?";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, problema.getDescricao());
            stmt.setString(2, problema.getTipo());
            stmt.setInt(3, problema.getGravidade());
            stmt.setInt(4, problema.getIdProblema());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Problema com ID " + problema.getIdProblema() + " não encontrado para atualização.");
                throw new SQLException("Problema não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar problema: " + e.getMessage());
            throw new SQLException("Erro ao atualizar problema.", e);
        }
    }

    public void removerProblema(int id) throws SQLException {
        String sql = "DELETE FROM T_ISL_PROBLEMA WHERE ID_PROBLEMA = ?";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                System.out.println("Problema com ID " + id + " não encontrado para remoção.");
                throw new SQLException("Problema não encontrado para remoção.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao remover problema: " + e.getMessage());
            throw new SQLException("Erro ao remover problema.", e);
        }
    }
}
