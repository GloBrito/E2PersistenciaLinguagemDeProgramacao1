package app.DAOs;

import app.helpers.DatabaseConnection;
import app.models.PlantaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlantaDAO {

    private Connection connection;

    // metodo construtor que inicializa a conexão com o banco de dados
    public PlantaDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // metodo para salvar uma nova planta no banco de dados
    public void salvar(PlantaModel planta) {
        String sql = "INSERT INTO planta (nome_cientifico, altura, tipo_planta) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, planta.getNomeCientifico());
            stmt.setDouble(2, planta.getAltura());
            stmt.setString(3, planta.getTipoPlanta());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para listar todas as plantas cadastradas no banco de dados
    public ObservableList<PlantaModel> listar() {
        ObservableList<PlantaModel> plantas = FXCollections.observableArrayList();
        String sql = "SELECT * FROM planta";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PlantaModel planta = new PlantaModel();
                planta.setId(rs.getInt("id"));
                planta.setNomeCientifico(rs.getString("nome_cientifico"));
                planta.setAltura(rs.getDouble("altura"));
                planta.setTipoPlanta(rs.getString("tipo_planta"));

                plantas.add(planta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plantas;
    }

    // metodo para buscar uma planta pelo nome cientifico
    public PlantaModel buscarPorNomeCientifico(String nomeCientifico) {
        PlantaModel planta = null;
        String sql = "SELECT * FROM planta WHERE nome_cientifico = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomeCientifico);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                planta = new PlantaModel();
                planta.setId(rs.getInt("id"));
                planta.setNomeCientifico(rs.getString("nome_cientifico"));
                planta.setAltura(rs.getDouble("altura"));
                planta.setTipoPlanta(rs.getString("tipo_planta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planta;
    }

    // metodo para atualizar uma planta existente no banco de dados
    public void atualizar(PlantaModel planta) {
        String sql = "UPDATE planta SET nome_cientifico = ?, altura = ?, tipo_planta = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, planta.getNomeCientifico());
            stmt.setDouble(2, planta.getAltura());
            stmt.setString(3, planta.getTipoPlanta());
            stmt.setInt(4, planta.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para deletar uma planta do banco de dados
    public void deletar(int id) {
        String sql = "DELETE FROM planta WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

