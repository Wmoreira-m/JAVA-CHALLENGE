package br.com.fiap.isolutions.dao;

import br.com.fiap.isolutions.conection.ConexaoBanco;
import br.com.fiap.isolutions.model.Veiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDao {

    public void inserirVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO T_ISL_VEICULO (MODELO, ANO, PLACA, ID_MARCA, ID_CLIENTE) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getAno());
            stmt.setString(3, veiculo.getPlaca());
            stmt.setInt(4, veiculo.getIdMarca());
            stmt.setInt(5, veiculo.getIdCliente());

            stmt.executeUpdate();

        } catch (SQLException e) {
            String errorMessage = "Erro ao inserir veículo: ";

            if (e.getErrorCode() == 2291) {
                // Especifica o erro de chave estrangeira para ID_CLIENTE
                errorMessage += "ID do cliente especificado não existe.";
                System.err.println("Erro ao inserir veículo: ID do cliente não encontrado na tabela CLIENTE.");
            } else if (e.getErrorCode() == 1) {
                // Erro de unicidade para o campo placa
                errorMessage += "Veículo com a mesma placa já cadastrado.";
                System.err.println("Erro ao inserir veículo: Placa duplicada.");
            } else {
                errorMessage += e.getMessage();
                System.err.println("Erro ao inserir veículo: " + e.getMessage());
            }

            throw new SQLException(errorMessage, e);
        }
    }



    public Veiculo buscarVeiculoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM T_ISL_VEICULO WHERE ID_VEICULO = ?";
        Veiculo veiculo = null;

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                veiculo = new Veiculo(
                        rs.getInt("ID_VEICULO"),
                        rs.getString("MODELO"),
                        rs.getString("ANO"),
                        rs.getString("PLACA"),
                        rs.getInt("ID_MARCA"),
                        rs.getInt("ID_CLIENTE")
                );
            }else {
                System.err.println("Veiculo com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículo por ID: " + e.getMessage());
            throw new SQLException("Erro ao buscar veículo", e);
        }
        return veiculo;
    }

    public List<Veiculo> buscarVeiculosPorIdCliente(int idCliente) throws SQLException {
        String sql = "SELECT * FROM T_ISL_VEICULO WHERE ID_CLIENTE = ?";
        List<Veiculo> veiculos = new ArrayList<>();

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                        rs.getInt("ID_VEICULO"),
                        rs.getString("MODELO"),
                        rs.getString("ANO"),
                        rs.getString("PLACA"),
                        rs.getInt("ID_MARCA"),
                        rs.getInt("ID_CLIENTE")
                );
                veiculos.add(veiculo);
            }

            if (veiculos.isEmpty()) {
                System.err.println("Nenhum veículo encontrado para o cliente com ID " + idCliente);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículos por ID do cliente: " + e.getMessage());
            throw new SQLException("Erro ao buscar veículos pelo ID do cliente.", e);
        }
        return veiculos;
    }


    public List<Veiculo> listarVeiculos() throws SQLException {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM T_ISL_VEICULO";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                        rs.getInt("ID_VEICULO"),
                        rs.getString("MODELO"),
                        rs.getString("ANO"),
                        rs.getString("PLACA"),
                        rs.getInt("ID_MARCA"),
                        rs.getInt("ID_CLIENTE")
                );
                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar veículos: " + e.getMessage());
            throw new SQLException("Erro ao listar veículos", e);
        }
        return veiculos;
    }

    public void atualizarVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE T_ISL_VEICULO SET MODELO = ?, ANO = ?, PLACA = ?, ID_MARCA = ?, ID_CLIENTE = ? WHERE ID_VEICULO = ?";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getAno());
            stmt.setString(3, veiculo.getPlaca());
            stmt.setInt(4, veiculo.getIdMarca());
            stmt.setInt(5, veiculo.getIdCliente());
            stmt.setInt(6, veiculo.getIdVeiculo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar veículo: " + e.getMessage());
            throw new SQLException("Erro ao atualizar veículo", e);
        }
    }

    public void removerVeiculo(int id) throws SQLException {
        String sql = "DELETE FROM T_ISL_VEICULO WHERE ID_VEICULO = ?";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao remover veículo: " + e.getMessage());
            throw new SQLException("Erro ao remover veículo.", e);
        }
    }
}