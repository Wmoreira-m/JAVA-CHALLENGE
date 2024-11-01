package br.com.fiap.isolutions.dao;

import br.com.fiap.isolutions.conection.ConexaoBanco;
import br.com.fiap.isolutions.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    public void inserirCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO T_ISL_CLIENTE (NOME, CPF, LOGIN, SENHA) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getLogin());
            stmt.setString(4, cliente.getSenha());
            stmt.executeUpdate();


        } catch (SQLException e) {
            String errorMessage = "Erro ao inserir cliente: ";
            if (e.getErrorCode() == 1) {
                errorMessage += "Já existe um cliente com esse CPF";
            } else {
                errorMessage += e.getMessage();
            }
            System.err.println(errorMessage);
            throw new SQLException(errorMessage, e);
        }
    }

    public Cliente buscarClientePorId(int id) throws SQLException {
        String sql = "SELECT * FROM T_ISL_CLIENTE WHERE ID_CLIENTE = ?";
        Cliente cliente = null;

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getString("NOME"),
                        rs.getString("CPF"),
                        rs.getInt("ID_CLIENTE"),
                        rs.getString("LOGIN"),
                        rs.getString("SENHA")
                );
            } else {
                System.err.println("Cliente com ID " + id + " não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente por ID: " + e.getMessage());
            throw new SQLException("Erro ao buscar cliente.", e);
        }
        return cliente;
    }

    public List<Cliente> listar() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM T_ISL_CLIENTE";

        try (Connection connection = ConexaoBanco.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("NOME"),
                        rs.getString("CPF"),
                        rs.getInt("ID_CLIENTE"),
                        rs.getString("LOGIN"),
                        rs.getString("SENHA")
                );
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
            throw new SQLException("Erro ao listar clientes.", e);
        }
        return clientes;
    }

    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE T_ISL_CLIENTE SET NOME = ?, CPF = ?, LOGIN = ?, SENHA = ? WHERE ID_CLIENTE = ?";

        try (Connection connection = ConexaoBanco.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getLogin());
            stmt.setString(4, cliente.getSenha());
            stmt.setInt(5, cliente.getIdCliente());
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Cliente não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
            throw new SQLException("Erro ao atualizar cliente.", e);
        }
    }

    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM T_ISL_CLIENTE WHERE ID_CLIENTE = ?";

        try (Connection connection = ConexaoBanco.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted == 0) {
                throw new SQLException("Cliente não encontrado para remoção.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao remover cliente: " + e.getMessage());
            throw new SQLException("Erro ao remover cliente.", e);
        }
    }
}
