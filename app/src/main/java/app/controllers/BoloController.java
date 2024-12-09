package app.controllers;

import app.DAOs.BoloDAO;
import app.helpers.Utils;
import app.models.BoloModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BoloController implements Initializable {

    @FXML
    private TextField txtSabor;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField txtPeso;

    @FXML
    private ChoiceBox<String> choiceBolos;

    private BoloDAO boloDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boloDAO = new BoloDAO();
        carregarBolos();
    }

    @FXML
    private void salvarBolo(ActionEvent event) {
        try {
            String sabor = txtSabor.getText();
            int quantidade = Integer.parseInt(txtQuantidade.getText());
            double peso = Double.parseDouble(txtPeso.getText());

            BoloModel bolo = new BoloModel(sabor, quantidade, peso);
            boloDAO.salvar(bolo);

            Utils.showInfo("Bolo salvo com sucesso!");
            carregarBolos();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao salvar o bolo: Valores numéricos inválidos.");
        } catch (Exception e) {
            Utils.showError("Erro ao salvar o bolo: " + e.getMessage());
        }
    }

    @FXML
    private void atualizarBolo(ActionEvent event) {
        try {
            String saborSelecionado = choiceBolos.getValue();
            if (saborSelecionado == null) {
                Utils.showError("Selecione um bolo para atualizar.");
                return;
            }

            BoloModel boloExistente = boloDAO.buscarPorSabor(saborSelecionado);
            if (boloExistente == null) {
                Utils.showError("Bolo não encontrado.");
                return;
            }

            String novoSabor = txtSabor.getText();
            int novaQuantidade = Integer.parseInt(txtQuantidade.getText());
            double novoPeso = Double.parseDouble(txtPeso.getText());

            boloExistente.setSabor(novoSabor);
            boloExistente.setQuantidade(novaQuantidade);
            boloExistente.setPeso(novoPeso);

            boloDAO.atualizar(boloExistente);

            Utils.showInfo("Bolo atualizado com sucesso!");
            carregarBolos();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao atualizar o bolo: Valores numéricos inválidos.");
        } catch (Exception e) {
            Utils.showError("Erro ao atualizar o bolo: " + e.getMessage());
        }
    }

    @FXML
    private void deletarBolo(ActionEvent event) {
        try {
            String saborSelecionado = choiceBolos.getValue();
            if (saborSelecionado == null) {
                Utils.showError("Selecione um bolo para deletar.");
                return;
            }

            boloDAO.deletarPorSabor(saborSelecionado);

            Utils.showInfo("Bolo deletado com sucesso!");
            carregarBolos();
            limparCampos();
        } catch (Exception e) {
            Utils.showError("Erro ao deletar o bolo: " + e.getMessage());
        }
    }

    private void carregarBolos() {
        try {
            ObservableList<String> bolos = boloDAO.listarSabores();
            choiceBolos.setItems(bolos);
        } catch (Exception e) {
            Utils.showError("Erro ao carregar os bolos: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtSabor.clear();
        txtQuantidade.clear();
        txtPeso.clear();
        choiceBolos.setValue(null);
    }
}

