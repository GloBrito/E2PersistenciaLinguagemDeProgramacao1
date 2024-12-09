package app.controllers;

import app.DAOs.DisciplinaDAO;
import app.helpers.Utils;
import app.models.DisciplinaModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DisciplinaController implements Initializable {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCargaHoraria;

    @FXML
    private TextField txtAreaConhecimento;

    @FXML
    private ChoiceBox<String> choiceDisciplinas;

    private DisciplinaDAO disciplinaDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        disciplinaDAO = new DisciplinaDAO();
        carregarDisciplinas();
    }

    @FXML
    private void salvarDisciplina(ActionEvent event) {
        try {
            String nome = txtNome.getText();
            String areaConhecimento = txtAreaConhecimento.getText();
            int cargaHoraria = Integer.parseInt(txtCargaHoraria.getText());

            DisciplinaModel disciplina = new DisciplinaModel(nome, areaConhecimento, cargaHoraria);
            disciplinaDAO.salvar(disciplina);

            Utils.showInfo("Disciplina salva com sucesso!");
            carregarDisciplinas();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao salvar a disciplina: Carga horária inválida.");
        } catch (Exception e) {
            Utils.showError("Erro ao salvar a disciplina: " + e.getMessage());
        }
    }

    @FXML
    private void atualizarDisciplina(ActionEvent event) {
        try {
            String nomeSelecionada = choiceDisciplinas.getValue();
            if (nomeSelecionada == null) {
                Utils.showError("Selecione uma disciplina para atualizar.");
                return;
            }

            DisciplinaModel disciplinaExistente = disciplinaDAO.buscarPorNome(nomeSelecionada);
            if (disciplinaExistente == null) {
                Utils.showError("Disciplina não encontrada.");
                return;
            }

            String novoNome = txtNome.getText();
            String novaAreaConhecimento = txtAreaConhecimento.getText();
            int novaCargaHoraria = Integer.parseInt(txtCargaHoraria.getText());

            disciplinaExistente.setNome(novoNome);
            disciplinaExistente.setAreaConhecimento(novaAreaConhecimento);
            disciplinaExistente.setCargaHoraria(novaCargaHoraria);

            disciplinaDAO.atualizar(disciplinaExistente);

            Utils.showInfo("Disciplina atualizada com sucesso!");
            carregarDisciplinas();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao atualizar a disciplina: Carga horária inválida.");
        } catch (Exception e) {
            Utils.showError("Erro ao atualizar a disciplina: " + e.getMessage());
        }
    }

    @FXML
    private void deletarDisciplina(ActionEvent event) {
        try {
            String nomeSelecionada = choiceDisciplinas.getValue();
            if (nomeSelecionada == null) {
                Utils.showError("Selecione uma disciplina para deletar.");
                return;
            }

            disciplinaDAO.deletarPorNome(nomeSelecionada);

            Utils.showInfo("Disciplina deletada com sucesso!");
            carregarDisciplinas();
            limparCampos();
        } catch (Exception e) {
            Utils.showError("Erro ao deletar a disciplina: " + e.getMessage());
        }
    }

    private void carregarDisciplinas() {
        try {
            ObservableList<String> disciplinas = disciplinaDAO.listarNomes();
            choiceDisciplinas.setItems(disciplinas);
        } catch (Exception e) {
            Utils.showError("Erro ao carregar as disciplinas: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtCargaHoraria.clear();
        txtAreaConhecimento.clear();
        choiceDisciplinas.setValue(null);
    }
}

