package app.DAOs;

import app.helpers.DatabaseConnection;
import app.models.CatedralModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CatedralDAO {

    private Connection connection;

    // metodo construtor que inicializa a conexão com o banco de dados
    public CatedralDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // metodo para salvar uma nova catedral no banco de dados
    public void salvar(CatedralModel catedral) {
        String sql = "INSERT INTO catedral (nome, estilo, localizacao, anoConstrucao) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, catedral.getNome());
            stmt.setString(2, catedral.getEstilo());
            stmt.setString(3, catedral.getLocalizacao());
            stmt.setInt(4, catedral.getAnoConstrucao());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para listar todas as catedrais cadastradas no banco de dados
    public ObservableList<CatedralModel> listar() {
        ObservableList<CatedralModel> catedrais = FXCollections.observableArrayList();
        String sql = "SELECT * FROM catedral";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CatedralModel catedral = new CatedralModel();
                catedral.setId(rs.getInt("id"));
                catedral.setNome(rs.getString("nome"));
                catedral.setEstilo(rs.getString("estilo"));
                catedral.setLocalizacao(rs.getString("localizacao"));
                catedral.setAnoConstrucao(rs.getInt("anoConstrucao"));

                catedrais.add(catedral);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return catedrais;
    }

    // metodo para buscar uma catedral pelo nome
    public CatedralModel buscarPorNome(String nome) {
        CatedralModel catedral = null;
        String sql = "SELECT * FROM catedral WHERE nome = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                catedral = new CatedralModel();
                catedral.setId(rs.getInt("id"));
                catedral.setNome(rs.getString("nome"));
                catedral.setEstilo(rs.getString("estilo"));
                catedral.setLocalizacao(rs.getString("localizacao"));
                catedral.setAnoConstrucao(rs.getInt("anoConstrucao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return catedral;
    }

    // metodo para atualizar uma catedral existente no banco de dados
    public void atualizar(CatedralModel catedral) {
        String sql = "UPDATE catedral SET nome = ?, estilo = ?, localizacao = ?, anoConstrucao = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, catedral.getNome());
            stmt.setString(2, catedral.getEstilo());
            stmt.setString(3, catedral.getLocalizacao());
            stmt.setInt(4, catedral.getAnoConstrucao());
            stmt.setInt(5, catedral.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para deletar uma catedral do banco de dados
    public void deletar(int id) {
        String sql = "DELETE FROM catedral WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

