package app.controllers;

import app.DAOs.FilmeDAO;
import app.helpers.Utils;
import app.models.FilmeModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FilmeController implements Initializable {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtGenero;

    @FXML
    private TextField txtDuracao;

    @FXML
    private ChoiceBox<String> choiceFilmes;

    private FilmeDAO filmeDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filmeDAO = new FilmeDAO();
        carregarFilmes();
    }

    @FXML
    private void salvarFilme(ActionEvent event) {
        try {
            String titulo = txtTitulo.getText();
            String genero = txtGenero.getText();
            int duracao = Integer.parseInt(txtDuracao.getText());

            FilmeModel filme = new FilmeModel(titulo, genero, duracao);
            filmeDAO.salvar(filme);

            Utils.showInfo("Filme salvo com sucesso!");
            carregarFilmes();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao salvar o filme: Duração inválida.");
        } catch (Exception e) {
            Utils.showError("Erro ao salvar o filme: " + e.getMessage());
        }
    }

    @FXML
    private void atualizarFilme(ActionEvent event) {
        try {
            String tituloSelecionado = choiceFilmes.getValue();
            if (tituloSelecionado == null) {
                Utils.showError("Selecione um filme para atualizar.");
                return;
            }

            FilmeModel filmeExistente = filmeDAO.buscarPorTitulo(tituloSelecionado);
            if (filmeExistente == null) {
                Utils.showError("Filme não encontrado.");
                return;
            }

            String novoTitulo = txtTitulo.getText();
            String novoGenero = txtGenero.getText();
            int novaDuracao = Integer.parseInt(txtDuracao.getText());

            filmeExistente.setTitulo(novoTitulo);
            filmeExistente.setGenero(novoGenero);
            filmeExistente.setDuracao(novaDuracao);

            filmeDAO.atualizar(filmeExistente);

            Utils.showInfo("Filme atualizado com sucesso!");
            carregarFilmes();
            limparCampos();
        } catch (NumberFormatException e) {
            Utils.showError("Erro ao atualizar o filme: Duração inválida.");
        } catch (Exception e) {
            Utils.showError("Erro ao atualizar o filme: " + e.getMessage());
        }
    }

    @FXML
    private void deletarFilme(ActionEvent event) {
        try {
            String tituloSelecionado = choiceFilmes.getValue();
            if (tituloSelecionado == null) {
                Utils.showError("Selecione um filme para deletar.");
                return;
            }

            filmeDAO.deletarPorTitulo(tituloSelecionado);

            Utils.showInfo("Filme deletado com sucesso!");
            carregarFilmes();
            limparCampos();
        } catch (Exception e) {
            Utils.showError("Erro ao deletar o filme: " + e.getMessage());
        }
    }

    private void carregarFilmes() {
        try {
            ObservableList<String> filmes = filmeDAO.listarTitulos();
            choiceFilmes.setItems(filmes);
        } catch (Exception e) {
            Utils.showError("Erro ao carregar os filmes: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtGenero.clear();
        txtDuracao.clear();
        choiceFilmes.setValue(null);
    }
}

