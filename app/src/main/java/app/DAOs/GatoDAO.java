package app.DAOs;

import app.helpers.DatabaseConnection;
import app.models.GatoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GatoDAO {

    private Connection connection;

    // metodo construtor que inicializa a conexão com o banco de dados
    public GatoDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // metodo para salvar um novo gato no banco de dados
    public void salvar(GatoModel gato) {
        String sql = "INSERT INTO gato (nome, idade, cor) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, gato.getNome());
            stmt.setInt(2, gato.getIdade());
            stmt.setString(3, gato.getCor());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para listar todos os gatos cadastrados no banco de dados
    public ObservableList<GatoModel> listar() {
        ObservableList<GatoModel> gatos = FXCollections.observableArrayList();
        String sql = "SELECT * FROM gato";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                GatoModel gato = new GatoModel();
                gato.setId(rs.getInt("id"));
                gato.setNome(rs.getString("nome"));
                gato.setIdade(rs.getInt("idade"));
                gato.setCor(rs.getString("cor"));

                gatos.add(gato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gatos;
    }

    // metodo para buscar um gato pelo nome
    public GatoModel buscarPorNome(String nome) {
        GatoModel gato = null;
        String sql = "SELECT * FROM gato WHERE nome = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                gato = new GatoModel();
                gato.setId(rs.getInt("id"));
                gato.setNome(rs.getString("nome"));
                gato.setIdade(rs.getInt("idade"));
                gato.setCor(rs.getString("cor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gato;
    }

    // metodo para atualizar um gato existente no banco de dados
    public void atualizar(GatoModel gato) {
        String sql = "UPDATE gato SET nome = ?, idade = ?, cor = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, gato.getNome());
            stmt.setInt(2, gato.getIdade());
            stmt.setString(3, gato.getCor());
            stmt.setInt(4, gato.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para deletar um gato do banco de dados
    public void deletar(int id) {
        String sql = "DELETE FROM gato WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

