package app.controllers;

import app.DAOs.LustreDAO;
import app.helpers.Utils;
import app.models.LustreModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LustreController implements Initializable {

    @FXML
    private TextField txtMaterial;

    @FXML
    private TextField txtPotencia;

    @FXML
    private TextField txtCor;

    @FXML
    private ChoiceBox<String> choiceLustres;

    private LustreDAO lustreDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lustreDAO = new LustreDAO();
        carregarLustres();
    }

    @FXML
    private void salvarLustre(ActionEvent event) {
        try {
            String material = txtMaterial.getText();
            int potencia = Integer.parseInt(txtPotencia.getText());
            String cor = txtCor.getText();

            LustreModel lustre = new LustreModel(material, potencia, cor);
            lustreDAO.salvar(lustre);

            Utils.showInfo("Lustre salvo com sucesso!");
            carregarLustres();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao salvar o lustre: Potência inválida.");
        } catch (Exception e) {
            Utils.showError("Erro ao salvar o lustre: " + e.getMessage());
        }
    }

    @FXML
    private void atualizarLustre(ActionEvent event) {
        try {
            String materialSelecionado = choiceLustres.getValue();
            if (materialSelecionado == null) {
                Utils.showError("Selecione um lustre para atualizar.");
                return;
            }

            LustreModel lustreExistente = lustreDAO.buscarPorMaterial(materialSelecionado);
            if (lustreExistente == null) {
                Utils.showError("Lustre não encontrado.");
                return;
            }

            String novoMaterial = txtMaterial.getText();
            int novaPotencia = Integer.parseInt(txtPotencia.getText());
            String novaCor = txtCor.getText();

            lustreExistente.setMaterial(novoMaterial);
            lustreExistente.setPotencia(novaPotencia);
            lustreExistente.setCor(novaCor);

            lustreDAO.atualizar(lustreExistente);

            Utils.showInfo("Lustre atualizado com sucesso!");
            carregarLustres();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao atualizar o lustre: Potência inválida.");
        } catch (Exception e) {
            Utils.showError("Erro ao atualizar o lustre: " + e.getMessage());
        }
    }

    @FXML
    private void deletarLustre(ActionEvent event) {
        try {
            String materialSelecionado = choiceLustres.getValue();
            if (materialSelecionado == null) {
                Utils.showError("Selecione um lustre para deletar.");
                return;
            }

            lustreDAO.deletarPorMaterial(materialSelecionado);

            Utils.showInfo("Lustre deletado com sucesso!");
            carregarLustres();
            limparCampos();
        } catch (Exception e) {
            Utils.showError("Erro ao deletar o lustre: " + e.getMessage());
        }
    }

    private void carregarLustres() {
        try {
            ObservableList<String> lustres = lustreDAO.listarMateriais();
            choiceLustres.setItems(lustres);
        } catch (Exception e) {
            Utils.showError("Erro ao carregar os lustres: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtMaterial.clear();
        txtPotencia.clear();
        txtCor.clear();
        choiceLustres.setValue(null);
    }
}

