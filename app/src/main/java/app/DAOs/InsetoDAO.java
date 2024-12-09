package app.DAOs;

import app.helpers.DatabaseConnection;
import app.models.InsetoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InsetoDAO {

    private Connection connection;

    // metodo construtor que inicializa a conexão com o banco de dados
    public InsetoDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // metodo para salvar um novo inseto no banco de dados
    public void salvar(InsetoModel inseto) {
        String sql = "INSERT INTO inseto (especie, tamanho, quantidade_patas) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, inseto.getEspecie());
            stmt.setDouble(2, inseto.getTamanho());
            stmt.setInt(3, inseto.getQuantidadePatas());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para listar todos os insetos cadastrados no banco de dados
    public ObservableList<InsetoModel> listar() {
        ObservableList<InsetoModel> insetos = FXCollections.observableArrayList();
        String sql = "SELECT * FROM inseto";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                InsetoModel inseto = new InsetoModel();
                inseto.setId(rs.getInt("id"));
                inseto.setEspecie(rs.getString("especie"));
                inseto.setTamanho(rs.getDouble("tamanho"));
                inseto.setQuantidadePatas(rs.getInt("quantidade_patas"));

                insetos.add(inseto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return insetos;
    }

    // metodo para buscar um inseto pela espécie
    public InsetoModel buscarPorEspecie(String especie) {
        InsetoModel inseto = null;
        String sql = "SELECT * FROM inseto WHERE especie = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, especie);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                inseto = new InsetoModel();
                inseto.setId(rs.getInt("id"));
                inseto.setEspecie(rs.getString("especie"));
                inseto.setTamanho(rs.getDouble("tamanho"));
                inseto.setQuantidadePatas(rs.getInt("quantidade_patas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inseto;
    }

    // metodo para atualizar um inseto existente no banco de dados
    public void atualizar(InsetoModel inseto) {
        String sql = "UPDATE inseto SET especie = ?, tamanho = ?, quantidade_patas = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, inseto.getEspecie());
            stmt.setDouble(2, inseto.getTamanho());
            stmt.setInt(3, inseto.getQuantidadePatas());
            stmt.setInt(4, inseto.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para deletar um inseto do banco de dados
    public void deletar(int id) {
        String sql = "DELETE FROM inseto WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

