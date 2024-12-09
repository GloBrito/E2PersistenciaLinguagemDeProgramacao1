package app.DAOs;

import app.helpers.DatabaseConnection;
import app.models.LustreModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LustreDAO {

    private Connection connection;

    // metodo construtor que inicializa a conexão com o banco de dados
    public LustreDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // metodo para salvar um novo lustre no banco de dados
    public void salvar(LustreModel lustre) {
        String sql = "INSERT INTO lustre (material, potencia, cor) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, lustre.getMaterial());
            stmt.setInt(2, lustre.getPotencia());
            stmt.setString(3, lustre.getCor());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para listar todos os lustres cadastrados no banco de dados
    public ObservableList<LustreModel> listar() {
        ObservableList<LustreModel> lustres = FXCollections.observableArrayList();
        String sql = "SELECT * FROM lustre";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                LustreModel lustre = new LustreModel();
                lustre.setId(rs.getInt("id"));
                lustre.setMaterial(rs.getString("material"));
                lustre.setPotencia(rs.getInt("potencia"));
                lustre.setCor(rs.getString("cor"));

                lustres.add(lustre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lustres;
    }

    // metodo para buscar um lustre pelo material
    public LustreModel buscarPorMaterial(String material) {
        LustreModel lustre = null;
        String sql = "SELECT * FROM lustre WHERE material = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, material);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lustre = new LustreModel();
                lustre.setId(rs.getInt("id"));
                lustre.setMaterial(rs.getString("material"));
                lustre.setPotencia(rs.getInt("potencia"));
                lustre.setCor(rs.getString("cor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lustre;
    }

    // metodo para atualizar um lustre existente no banco de dados
    public void atualizar(LustreModel lustre) {
        String sql = "UPDATE lustre SET material = ?, potencia = ?, cor = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, lustre.getMaterial());
            stmt.setInt(2, lustre.getPotencia());
            stmt.setString(3, lustre.getCor());
            stmt.setInt(4, lustre.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para deletar um lustre do banco de dados
    public void deletar(int id) {
        String sql = "DELETE FROM lustre WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

