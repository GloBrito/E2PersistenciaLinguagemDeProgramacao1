package app.controllers;

import app.DAOs.PinturaDAO;
import app.helpers.Utils;
import app.models.PinturaModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PinturaController implements Initializable {

    @FXML
    private TextField txtNomeArtista;

    @FXML
    private TextField txtAnoCriacao;

    @FXML
    private TextField txtTecnica;

    @FXML
    private ChoiceBox<String> choicePinturas;

    private PinturaDAO pinturaDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pinturaDAO = new PinturaDAO();
        carregarPinturas();
    }

    @FXML
    private void salvarPintura(ActionEvent event) {
        try {
            String nomeArtista = txtNomeArtista.getText();
            int anoCriacao = Integer.parseInt(txtAnoCriacao.getText());
            String tecnica = txtTecnica.getText();

            PinturaModel pintura = new PinturaModel(nomeArtista, anoCriacao, tecnica);
            pinturaDAO.salvar(pintura);

            Utils.showInfo("Pintura salva com sucesso!");
            carregarPinturas();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao salvar a pintura: Ano de criação inválido.");
        } catch (Exception e) {
            Utils.showError("Erro ao salvar a pintura: " + e.getMessage());
        }
    }

    @FXML
    private void atualizarPintura(ActionEvent event) {
        try {
            String nomeArtistaSelecionado = choicePinturas.getValue();
            if (nomeArtistaSelecionado == null) {
                Utils.showError("Selecione uma pintura para atualizar.");
                return;
            }

            PinturaModel pinturaExistente = pinturaDAO.buscarPorArtista(nomeArtistaSelecionado);
            if (pinturaExistente == null) {
                Utils.showError("Pintura não encontrada.");
                return;
            }

            String novoNomeArtista = txtNomeArtista.getText();
            int novoAnoCriacao = Integer.parseInt(txtAnoCriacao.getText());
            String novaTecnica = txtTecnica.getText();

            pinturaExistente.setNomeArtista(novoNomeArtista);
            pinturaExistente.setAnoCriacao(novoAnoCriacao);
            pinturaExistente.setTecnica(novaTecnica);

            pinturaDAO.atualizar(pinturaExistente);

            Utils.showInfo("Pintura atualizada com sucesso!");
            carregarPinturas();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao atualizar a pintura: Ano de criação inválido.");
        } catch (Exception e) {
            Utils.showError("Erro ao atualizar a pintura: " + e.getMessage());
        }
    }

    @FXML
    private void deletarPintura(ActionEvent event) {
        try {
            String nomeArtistaSelecionado = choicePinturas.getValue();
            if (nomeArtistaSelecionado == null) {
                Utils.showError("Selecione uma pintura para deletar.");
                return;
            }

            pinturaDAO.deletarPorArtista(nomeArtistaSelecionado);

            Utils.showInfo("Pintura deletada com sucesso!");
            carregarPinturas();
            limparCampos();
        } catch (Exception e) {
            Utils.showError("Erro ao deletar a pintura: " + e.getMessage());
        }
    }

    private void carregarPinturas() {
        try {
            ObservableList<String> pinturas = pinturaDAO.listarArtistas();
            choicePinturas.setItems(pinturas);
        } catch (Exception e) {
            Utils.showError("Erro ao carregar as pinturas: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtNomeArtista.clear();
        txtAnoCriacao.clear();
        txtTecnica.clear();
        choicePinturas.setValue(null);
    }
}

