package app.controllers;

import app.DAOs.InsetoDAO;
import app.helpers.Utils;
import app.models.InsetoModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InsetoController implements Initializable {

    @FXML
    private TextField txtEspecie;

    @FXML
    private TextField txtTamanho;

    @FXML
    private TextField txtQuantidadePatas;

    @FXML
    private ChoiceBox<String> choiceInsetos;

    private InsetoDAO insetoDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        insetoDAO = new InsetoDAO();
        carregarInsetos();
    }

    @FXML
    private void salvarInseto(ActionEvent event) {
        try {
            String especie = txtEspecie.getText();
            double tamanho = Double.parseDouble(txtTamanho.getText());
            int quantidadePatas = Integer.parseInt(txtQuantidadePatas.getText());

            InsetoModel inseto = new InsetoModel(especie, tamanho, quantidadePatas);
            insetoDAO.salvar(inseto);

            Utils.showInfo("Inseto salvo com sucesso!");
            carregarInsetos();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao salvar o inseto: Valores numéricos inválidos.");
        } catch (Exception e) {
            Utils.showError("Erro ao salvar o inseto: " + e.getMessage());
        }
    }

    @FXML
    private void atualizarInseto(ActionEvent event) {
        try {
            String especieSelecionada = choiceInsetos.getValue();
            if (especieSelecionada == null) {
                Utils.showError("Selecione um inseto para atualizar.");
                return;
            }

            InsetoModel insetoExistente = insetoDAO.buscarPorEspecie(especieSelecionada);
            if (insetoExistente == null) {
                Utils.showError("Inseto não encontrado.");
                return;
            }

            String novaEspecie = txtEspecie.getText();
            double novoTamanho = Double.parseDouble(txtTamanho.getText());
            int novaQuantidadePatas = Integer.parseInt(txtQuantidadePatas.getText());

            insetoExistente.setEspecie(novaEspecie);
            insetoExistente.setTamanho(novoTamanho);
            insetoExistente.setQuantidadePatas(novaQuantidadePatas);

            insetoDAO.atualizar(insetoExistente);

            Utils.showInfo("Inseto atualizado com sucesso!");
            carregarInsetos();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao atualizar o inseto: Valores numéricos inválidos.");
        } catch (Exception e) {
            Utils.showError("Erro ao atualizar o inseto: " + e.getMessage());
        }
    }

    @FXML
    private void deletarInseto(ActionEvent event) {
        try {
            String especieSelecionada = choiceInsetos.getValue();
            if (especieSelecionada == null) {
                Utils.showError("Selecione um inseto para deletar.");
                return;
            }

            insetoDAO.deletarPorEspecie(especieSelecionada);

            Utils.showInfo("Inseto deletado com sucesso!");
            carregarInsetos();
            limparCampos();
        } catch (Exception e) {
            Utils.showError("Erro ao deletar o inseto: " + e.getMessage());
        }
    }

    private void carregarInsetos() {
        try {
            ObservableList<String> insetos = insetoDAO.listarEspecies();
            choiceInsetos.setItems(insetos);
        } catch (Exception e) {
            Utils.showError("Erro ao carregar os insetos: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtEspecie.clear();
        txtTamanho.clear();
        txtQuantidadePatas.clear();
        choiceInsetos.setValue(null);
    }
}

