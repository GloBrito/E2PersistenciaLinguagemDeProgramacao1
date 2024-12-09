package app.DAOs;

import app.helpers.DatabaseConnection;
import app.models.FilmeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmeDAO {

    private Connection connection;

    // metodo construtor que inicializa a conexão com o banco de dados
    public FilmeDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // metodo para salvar um novo filme no banco de dados
    public void salvar(FilmeModel filme) {
        String sql = "INSERT INTO filme (titulo, genero, duracao) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getGenero());
            stmt.setInt(3, filme.getDuracao());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para listar todos os filmes cadastrados no banco de dados
    public ObservableList<FilmeModel> listar() {
        ObservableList<FilmeModel> filmes = FXCollections.observableArrayList();
        String sql = "SELECT * FROM filme";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                FilmeModel filme = new FilmeModel();
                filme.setId(rs.getInt("id"));
                filme.setTitulo(rs.getString("titulo"));
                filme.setGenero(rs.getString("genero"));
                filme.setDuracao(rs.getInt("duracao"));

                filmes.add(filme);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filmes;
    }

    // metodo para buscar um filme pelo titulo
    public FilmeModel buscarPorTitulo(String titulo) {
        FilmeModel filme = null;
        String sql = "SELECT * FROM filme WHERE titulo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                filme = new FilmeModel();
                filme.setId(rs.getInt("id"));
                filme.setTitulo(rs.getString("titulo"));
                filme.setGenero(rs.getString("genero"));
                filme.setDuracao(rs.getInt("duracao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filme;
    }

    // metodo para atualizar um filme existente no banco de dados
    public void atualizar(FilmeModel filme) {
        String sql = "UPDATE filme SET titulo = ?, genero = ?, duracao = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getGenero());
            stmt.setInt(3, filme.getDuracao());
            stmt.setInt(4, filme.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para deletar um filme do banco de dados
    public void deletar(int id) {
        String sql = "DELETE FROM filme WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

