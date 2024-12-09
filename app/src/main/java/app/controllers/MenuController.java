package app.controllers;

import app.helpers.Utils;
import javafx.fxml.FXML;

import java.io.IOException;

public class MenuController {
    @FXML
    protected void onCanetaButtonClick() throws IOException {
        Main.setRoot("/caneta");
    }

    @FXML
    protected void onCatedralButtonClick() throws IOException {
        Main.setRoot("/catedral");
    }

    @FXML
    protected void onBoloButtonClick() throws IOException {
        Main.setRoot("/bolo");
    }

    @FXML
    protected void onDisciplinaButtonClick() throws IOException {
        Main.setRoot("/disciplina");
    }

    @FXML
    protected void onFilmerButtonClick() throws IOException {
        Main.setRoot("/filme");
    }

    @FXML
    protected void onGatoButtonClick() throws IOException {
        Main.setRoot("/gato");
    }

    @FXML
    protected void onInsetoClick() throws IOException {
        Main.setRoot("/inseto");
    }

    @FXML
    protected void onLustreButtonClick() throws IOException {
        Main.setRoot("/lustre");
    }

    @FXML
    protected void onPinturaButtonClick() throws IOException {
        Main.setRoot("/pintura");
    }

    @FXML
    protected void onPlantaButtonClick() throws IOException {
        Main.setRoot("/planta");
    }

    @FXML
    private void fillDatabase() {
        try {
            Utils.executeSQLFromFile("/assets/sql/schema.sql");
            Utils.setAlert("CONFIRMATION", "Preenchimento do banco", "Tabelas criadas com sucesso!");
        } catch (Exception e) {
            Utils.setAlert("ERROR", "Preenchimento do banco", "Erro ao criar as tabelas: " + e.getMessage());
        }
    }
}
