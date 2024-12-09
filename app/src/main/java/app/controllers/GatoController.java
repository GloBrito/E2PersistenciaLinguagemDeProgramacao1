package app.controllers;

import app.DAOs.GatoDAO;
import app.helpers.Utils;
import app.models.GatoModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GatoController implements Initializable {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtCor;

    @FXML
    private ChoiceBox<String> choiceGatos;

    private GatoDAO gatoDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gatoDAO = new GatoDAO();
        carregarGatos();
    }

    @FXML
    private void salvarGato(ActionEvent event) {
        try {
            String nome = txtNome.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            String cor = txtCor.getText();

            GatoModel gato = new GatoModel(nome, idade, cor);
            gatoDAO.salvar(gato);

            Utils.showInfo("Gato salvo com sucesso!");
            carregarGatos();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao salvar o gato: Valores numéricos inválidos.");
        } catch (Exception e) {
            Utils.showError("Erro ao salvar o gato: " + e.getMessage());
        }
    }

    @FXML
    private void atualizarGato(ActionEvent event) {
        try {
            String nomeSelecionado = choiceGatos.getValue();
            if (nomeSelecionado == null) {
                Utils.showError("Selecione um gato para atualizar.");
                return;
            }

            GatoModel gatoExistente = gatoDAO.buscarPorNome(nomeSelecionado);
            if (gatoExistente == null) {
                Utils.showError("Gato não encontrado.");
                return;
            }

            String novoNome = txtNome.getText();
            int novaIdade = Integer.parseInt(txtIdade.getText());
            String novaCor = txtCor.getText();

            gatoExistente.setNome(novoNome);
            gatoExistente.setIdade(novaIdade);
            gatoExistente.setCor(novaCor);

            gatoDAO.atualizar(gatoExistente);

            Utils.showInfo("Gato atualizado com sucesso!");
            carregarGatos();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao atualizar o gato: Valores numéricos inválidos.");
        } catch (Exception e) {
            Utils.showError("Erro ao atualizar o gato: " + e.getMessage());
        }
    }

    @FXML
    private void deletarGato(ActionEvent event) {
        try {
            String nomeSelecionado = choiceGatos.getValue();
            if (nomeSelecionado == null) {
                Utils.show

