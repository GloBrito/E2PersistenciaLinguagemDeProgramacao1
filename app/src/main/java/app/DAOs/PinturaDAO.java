package app.DAOs;

import app.helpers.DatabaseConnection;
import app.models.PinturaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PinturaDAO {

    private Connection connection;

    // metodo construtor que inicializa a conexão com o banco de dados
    public PinturaDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // metodo para salvar uma nova pintura no banco de dados
    public void salvar(PinturaModel pintura) {
        String sql = "INSERT INTO pintura (artista, titulo, ano) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pintura.getArtista());
            stmt.setString(2, pintura.getTitulo());
            stmt.setInt(3, pintura.getAno());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para listar todas as pinturas cadastradas no banco de dados
    public ObservableList<PinturaModel> listar() {
        ObservableList<PinturaModel> pinturas = FXCollections.observableArrayList();
        String sql = "SELECT * FROM pintura";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PinturaModel pintura = new PinturaModel();
                pintura.setId(rs.getInt("id"));
                pintura.setArtista(rs.getString("artista"));
                pintura.setTitulo(rs.getString("titulo"));
                pintura.setAno(rs.getInt("ano"));

                pinturas.add(pintura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pinturas;
    }

    // metodo para buscar uma pintura pelo título
    public PinturaModel buscarPorTitulo(String titulo) {
        PinturaModel pintura = null;
        String sql = "SELECT * FROM pintura WHERE titulo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pintura = new PinturaModel();
                pintura.setId(rs.getInt("id"));
                pintura.setArtista(rs.getString("artista"));
                pintura.setTitulo(rs.getString("titulo"));
                pintura.setAno(rs.getInt("ano"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pintura;
    }

    // metodo para atualizar uma pintura existente no banco de dados
    public void atualizar(PinturaModel pintura) {
        String sql = "UPDATE pintura SET artista = ?, titulo = ?, ano = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pintura.getArtista());
            stmt.setString(2, pintura.getTitulo());
            stmt.setInt(3, pintura.getAno());
            stmt.setInt(4, pintura.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para deletar uma pintura do banco de dados
    public void deletar(int id) {
        String sql = "DELETE FROM pintura WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

