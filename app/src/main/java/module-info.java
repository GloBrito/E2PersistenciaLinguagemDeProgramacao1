module org.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires dotenv.java;


    opens app.DAOs to javafx.fxml;
    exports app.DAOs;
    exports app.controllers;
    opens app.controllers to javafx.fxml;
}