package com.example.demoia;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainController {

    public void goToLogin(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        Button source = (Button) event.getSource();
        String pageName = source.getText().toLowerCase();
        String fxmlPage = pageName + "Page.fxml";

        Parent root = FXMLLoader.load(getClass().getResource(fxmlPage));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle(pageName.toUpperCase()+"...");
        stage.setScene(new Scene(root, 600, 400));
    }
}
