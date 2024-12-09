package app.controllers;

import app.DAOs.CatedralDAO;
import app.helpers.Utils;
import app.models.CatedralModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CatedralController implements Initializable {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtLocalizacao;

    @FXML
    private TextField txtAnoConstrucao;

    @FXML
    private ChoiceBox<String> choiceCatedrais;

    private CatedralDAO catedralDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        catedralDAO = new CatedralDAO();
        carregarCatedrais();
    }

    @FXML
    private void salvarCatedral(ActionEvent event) {
        try {
            String nome = txtNome.getText();
            String localizacao = txtLocalizacao.getText();
            int anoConstrucao = Integer.parseInt(txtAnoConstrucao.getText());

            CatedralModel catedral = new CatedralModel(nome, localizacao, anoConstrucao);
            catedralDAO.salvar(catedral);

            Utils.showInfo("Catedral salva com sucesso!");
            carregarCatedrais();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao salvar a catedral: Ano de construção inválido.");
        } catch (Exception e) {
            Utils.showError("Erro ao salvar a catedral: " + e.getMessage());
        }
    }

    @FXML
    private void atualizarCatedral(ActionEvent event) {
        try {
            String nomeSelecionada = choiceCatedrais.getValue();
            if (nomeSelecionada == null) {
                Utils.showError("Selecione uma catedral para atualizar.");
                return;
            }

            CatedralModel catedralExistente = catedralDAO.buscarPorNome(nomeSelecionada);
            if (catedralExistente == null) {
                Utils.showError("Catedral não encontrada.");
                return;
            }

            String novoNome = txtNome.getText();
            String novaLocalizacao = txtLocalizacao.getText();
            int novoAnoConstrucao = Integer.parseInt(txtAnoConstrucao.getText());

            catedralExistente.setNome(novoNome);
            catedralExistente.setLocalizacao(novaLocalizacao);
            catedralExistente.setAnoConstrucao(novoAnoConstrucao);

            catedralDAO.atualizar(catedralExistente);

            Utils.showInfo("Catedral atualizada com sucesso!");
            carregarCatedrais();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao atualizar a catedral: Ano de construção inválido.");
        } catch (Exception e) {
            Utils.showError("Erro ao atualizar a catedral: " + e.getMessage());
        }
    }

    @FXML
    private void deletarCatedral(ActionEvent event) {
        try {
            String nomeSelecionada = choiceCatedrais.getValue();
            if (nomeSelecionada == null) {
                Utils.showError("Selecione uma catedral para deletar.");
                return;
            }

            catedralDAO.deletarPorNome(nomeSelecionada);

            Utils.showInfo("Catedral deletada com sucesso!");
            carregarCatedrais();
            limparCampos();
        } catch (Exception e) {
            Utils.showError("Erro ao deletar a catedral: " + e.getMessage());
        }
    }

    private void carregarCatedrais() {
        try {
            ObservableList<String> catedrais = catedralDAO.listarNomes();
            choiceCatedrais.setItems(catedrais);
        } catch (Exception e) {
            Utils.showError("Erro ao carregar as catedrais: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtLocalizacao.clear();
        txtAnoConstrucao.clear();
        choiceCatedrais.setValue(null);
    }
}

