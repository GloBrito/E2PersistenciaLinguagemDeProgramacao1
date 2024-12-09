package app.controllers;

import app.DAOs.PlantaDAO;
import app.helpers.Utils;
import app.models.PlantaModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PlantaController implements Initializable {

    @FXML
    private TextField txtNomeCientifico;

    @FXML
    private TextField txtAltura;

    @FXML
    private TextField txtTipoPlanta;

    @FXML
    private ChoiceBox<String> choicePlantas;

    private PlantaDAO plantaDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        plantaDAO = new PlantaDAO();
        carregarPlantas();
    }

    @FXML
    private void salvarPlanta(ActionEvent event) {
        try {
            String nomeCientifico = txtNomeCientifico.getText();
            double altura = Double.parseDouble(txtAltura.getText());
            String tipoPlanta = txtTipoPlanta.getText();

            PlantaModel planta = new PlantaModel(nomeCientifico, altura, tipoPlanta);
            plantaDAO.salvar(planta);

            Utils.showInfo("Planta salva com sucesso!");
            carregarPlantas();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao salvar a planta: Altura inválida.");
        } catch (Exception e) {
            Utils.showError("Erro ao salvar a planta: " + e.getMessage());
        }
    }

    @FXML
    private void atualizarPlanta(ActionEvent event) {
        try {
            String nomeCientificoSelecionado = choicePlantas.getValue();
            if (nomeCientificoSelecionado == null) {
                Utils.showError("Selecione uma planta para atualizar.");
                return;
            }

            PlantaModel plantaExistente = plantaDAO.buscarPorNomeCientifico(nomeCientificoSelecionado);
            if (plantaExistente == null) {
                Utils.showError("Planta não encontrada.");
                return;
            }

            String novoNomeCientifico = txtNomeCientifico.getText();
            double novaAltura = Double.parseDouble(txtAltura.getText());
            String novoTipoPlanta = txtTipoPlanta.getText();

            plantaExistente.setNomeCientifico(novoNomeCientifico);
            plantaExistente.setAltura(novaAltura);
            plantaExistente.setTipoPlanta(novoTipoPlanta);

            plantaDAO.atualizar(plantaExistente);

            Utils.showInfo("Planta atualizada com sucesso!");
            carregarPlantas();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao atualizar a planta: Altura inválida.");
        } catch (Exception e) {
            Utils.showError("Erro ao atualizar a planta: " + e.getMessage());
        }
    }

    @FXML
    private void deletarPlanta(ActionEvent event) {
        try {
            String nomeCientificoSelecionado = choicePlantas.getValue();
            if (nomeCientificoSelecionado == null) {
                Utils.showError("Selecione uma planta para deletar.");
                return;
            }

            plantaDAO.deletarPorNomeCientifico(nomeCientificoSelecionado);

            Utils.showInfo("Planta deletada com sucesso!");
            carregarPlantas();
            limparCampos();
        } catch (Exception e) {
            Utils.showError("Erro ao deletar a planta: " + e.getMessage());
        }
    }

    private void carregarPlantas() {
        try {
            ObservableList<String> plantas = plantaDAO.listarNomeCientifico();
            choicePlantas.setItems(plantas);
        } catch (Exception e) {
            Utils.showError("Erro ao carregar as plantas: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtNomeCientifico.clear();
        txtAltura.clear();
        txtTipoPlanta.clear();
        choicePlantas.setValue(null);
    }
}

