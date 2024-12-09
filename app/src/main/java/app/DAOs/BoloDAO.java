package app.DAOs;

import app.helpers.DatabaseConnection;
import app.models.BoloModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoloDAO {

    private Connection connection;

    public BoloDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    //metodo para salvar um novo bolo
    public void salvar(BoloModel bolo) {
        String sql = "INSERT INTO bolo (nome, sabor, quantidade) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bolo.getNome());
            stmt.setString(2, bolo.getSabor());
            stmt.setInt(3, bolo.getQuantidade());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //metodo para listar todos os bolos
    public ObservableList<BoloModel> listar() {
        ObservableList<BoloModel> bolos = FXCollections.observableArrayList();
        String sql = "SELECT * FROM bolo";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                BoloModel bolo = new BoloModel();
                bolo.setId(rs.getInt("id"));
                bolo.setNome(rs.getString("nome"));
                bolo.setSabor(rs.getString("sabor"));
                bolo.setQuantidade(rs.getInt("quantidade"));

                bolos.add(bolo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bolos;
    }

    //metodo para buscar um bolo por nome
    public BoloModel buscarPorNome(String nome) {
        BoloModel bolo = null;
        String sql = "SELECT * FROM bolo WHERE nome = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                bolo = new BoloModel();
                bolo.setId(rs.getInt("id"));
                bolo.setNome(rs.getString("nome"));
                bolo.setSabor(rs.getString("sabor"));
                bolo.setQuantidade(rs.getInt("quantidade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bolo;
    }

    //metodo para atualizar um bolo existente
    public void atualizar(BoloModel bolo) {
        String sql = "UPDATE bolo SET nome = ?, sabor = ?, quantidade = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bolo.getNome());
            stmt.setString(2, bolo.getSabor());
            stmt.setInt(3, bolo.getQuantidade());
            stmt.setInt(4, bolo.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //metodo para deletar um bolo
    public void deletar(int id) {
        String sql = "DELETE FROM bolo WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

