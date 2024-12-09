package app.DAOs;

import app.helpers.DatabaseConnection;
import app.models.CanetaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CanetaDAO {

    private Connection connection;

    // metodo construtor que inicializa a conexão com o banco de dados
    public CanetaDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // metodo para salvar uma nova caneta no banco de dados
    public void salvar(CanetaModel caneta) {
        String sql = "INSERT INTO caneta (cor, tipo, quantidade) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, caneta.getCor());
            stmt.setString(2, caneta.getTipo());
            stmt.setInt(3, caneta.getQuantidade());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para listar todas as canetas cadastradas no banco de dados
    public ObservableList<CanetaModel> listar() {
        ObservableList<CanetaModel> canetas = FXCollections.observableArrayList();
        String sql = "SELECT * FROM caneta";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CanetaModel caneta = new CanetaModel();
                caneta.setId(rs.getInt("id"));
                caneta.setCor(rs.getString("cor"));
                caneta.setTipo(rs.getString("tipo"));
                caneta.setQuantidade(rs.getInt("quantidade"));

                canetas.add(caneta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return canetas;
    }

    // metodo para buscar uma caneta pelo nome ou tipo
    public CanetaModel buscarPorTipo(String tipo) {
        CanetaModel caneta = null;
        String sql = "SELECT * FROM caneta WHERE tipo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                caneta = new CanetaModel();
                caneta.setId(rs.getInt("id"));
                caneta.setCor(rs.getString("cor"));
                caneta.setTipo(rs.getString("tipo"));
                caneta.setQuantidade(rs.getInt("quantidade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return caneta;
    }

    // metodo para atualizar uma caneta existente no banco de dados
    public void atualizar(CanetaModel caneta) {
        String sql = "UPDATE caneta SET cor = ?, tipo = ?, quantidade = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, caneta.getCor());
            stmt.setString(2, caneta.getTipo());
            stmt.setInt(3, caneta.getQuantidade());
            stmt.setInt(4, caneta.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para deletar uma caneta do banco de dados
    public void deletar(int id) {
        String sql = "DELETE FROM caneta WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

