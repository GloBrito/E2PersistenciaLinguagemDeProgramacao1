package app.DAOs;

import app.helpers.DatabaseConnection;
import app.models.DisciplinaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DisciplinaDAO {

    private Connection connection;

    // metodo construtor que inicializa a conexão com o banco de dados
    public DisciplinaDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // metodo para salvar uma nova disciplina no banco de dados
    public void salvar(DisciplinaModel disciplina) {
        String sql = "INSERT INTO disciplina (nome, cargaHoraria, areaConhecimento) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getCargaHoraria());
            stmt.setString(3, disciplina.getAreaConhecimento());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para listar todas as disciplinas cadastradas no banco de dados
    public ObservableList<DisciplinaModel> listar() {
        ObservableList<DisciplinaModel> disciplinas = FXCollections.observableArrayList();
        String sql = "SELECT * FROM disciplina";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DisciplinaModel disciplina = new DisciplinaModel();
                disciplina.setId(rs.getInt("id"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("cargaHoraria"));
                disciplina.setAreaConhecimento(rs.getString("areaConhecimento"));

                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disciplinas;
    }

    // metodo para buscar uma disciplina pelo nome
    public DisciplinaModel buscarPorNome(String nome) {
        DisciplinaModel disciplina = null;
        String sql = "SELECT * FROM disciplina WHERE nome = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                disciplina = new DisciplinaModel();
                disciplina.setId(rs.getInt("id"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("cargaHoraria"));
                disciplina.setAreaConhecimento(rs.getString("areaConhecimento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disciplina;
    }

    // metodo para atualizar uma disciplina existente no banco de dados
    public void atualizar(DisciplinaModel disciplina) {
        String sql = "UPDATE disciplina SET nome = ?, cargaHoraria = ?, areaConhecimento = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getCargaHoraria());
            stmt.setString(3, disciplina.getAreaConhecimento());
            stmt.setInt(4, disciplina.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para deletar uma disciplina do banco de dados
    public void deletar(int id) {
        String sql = "DELETE FROM disciplina WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

