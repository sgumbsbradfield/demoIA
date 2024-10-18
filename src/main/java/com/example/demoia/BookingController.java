package com.example.demoia;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingController {

    public void goToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("LOGIN...");
        stage.setScene(new Scene(root,600,400));
    }
}
