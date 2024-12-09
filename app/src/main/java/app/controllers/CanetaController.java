package app.controllers;

import app.DAOs.CanetaDAO;
import app.helpers.Utils;
import app.models.CanetaModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CanetaController implements Initializable {

    @FXML
    private TextField txtCor;

    @FXML
    private TextField txtTamanho;

    @FXML
    private TextField txtMarca;

    @FXML
    private ChoiceBox<String> choiceCanetas;

    private CanetaDAO canetaDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canetaDAO = new CanetaDAO();
        carregarCanetas();
    }

    @FXML
    private void salvarCaneta(ActionEvent event) {
        try {
            String cor = txtCor.getText();
            double tamanho = Double.parseDouble(txtTamanho.getText());
            String marca = txtMarca.getText();

            CanetaModel caneta = new CanetaModel(cor, tamanho, marca);
            canetaDAO.salvar(caneta);

            Utils.showInfo("Caneta salva com sucesso!");
            carregarCanetas();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao salvar a caneta: Valores numéricos inválidos.");
        } catch (Exception e) {
            Utils.showError("Erro ao salvar a caneta: " + e.getMessage());
        }
    }

    @FXML
    private void atualizarCaneta(ActionEvent event) {
        try {
            String marcaSelecionada = choiceCanetas.getValue();
            if (marcaSelecionada == null) {
                Utils.showError("Selecione uma caneta para atualizar.");
                return;
            }

            CanetaModel canetaExistente = canetaDAO.buscarPorMarca(marcaSelecionada);
            if (canetaExistente == null) {
                Utils.showError("Caneta não encontrada.");
                return;
            }

            String novaCor = txtCor.getText();
            double novoTamanho = Double.parseDouble(txtTamanho.getText());
            String novaMarca = txtMarca.getText();

            canetaExistente.setCor(novaCor);
            canetaExistente.setTamanho(novoTamanho);
            canetaExistente.setMarca(novaMarca);

            canetaDAO.atualizar(canetaExistente);

            Utils.showInfo("Caneta atualizada com sucesso!");
            carregarCanetas();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao atualizar a caneta: Valores numéricos inválidos.");
        } catch (Exception e) {
            Utils.showError("Erro ao atualizar a caneta: " + e.getMessage());
        }
    }

    @FXML
    private void deletarCaneta(ActionEvent event) {
        try {
            String marcaSelecionada = choiceCanetas.getValue();
            if (marcaSelecionada == null) {
                Utils.showError("Selecione uma caneta para deletar.");
                return;
            }

            canetaDAO.deletarPorMarca(marcaSelecionada);

            Utils.showInfo("Caneta deletada com sucesso!");
            carregarCanetas();
            limparCampos();
        } catch (Exception e) {
            Utils.showError("Erro ao deletar a caneta: " + e.getMessage());
        }
    }

    private void carregarCanetas() {
        try {
            ObservableList<String> canetas = canetaDAO.listarMarcas();
            choiceCanetas.setItems(canetas);
        } catch (Exception e) {
            Utils.showError("Erro ao carregar as canetas: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtCor.clear();
        txtTamanho.clear();
        txtMarca.clear();
        choiceCanetas.setValue(null);
    }
}

