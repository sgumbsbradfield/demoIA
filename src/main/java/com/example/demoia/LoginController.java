package com.example.demoia;

import javafx.event.ActionEvent; // Correct import for JavaFX ActionEvent
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private TextField passwordTextField;
    @FXML
    private CheckBox showPasswordCheckBox;

    public void goTo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("timetablePage.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("TIMETABLE...");
        stage.setScene(new Scene(root, 600, 400));
    }

    public void goToTimetable(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("timetablePage.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("TIMETABLE...");
        stage.setScene(new Scene(root, 600, 400));
    }

    public void goToMap(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mapPage.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("MAP...");
        stage.setScene(new Scene(root, 600, 400));
    }

    public void goToBooking(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("bookingPage.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("BOOKING...");
        stage.setScene(new Scene(root, 600, 400));
    }

    // Method to handle the CheckBox action to toggle password visibility
    @FXML
    private void togglePasswordVisibility() {
        if (showPasswordCheckBox.isSelected()) {
            // Show the password in plain text by making TextField visible
            passwordTextField.setText(passwordField.getText());
            passwordField.setVisible(false);
            passwordTextField.setVisible(true);
        } else {
            // Hide the password and switch back to PasswordField
            passwordField.setText(passwordTextField.getText());
            passwordTextField.setVisible(false);
            passwordField.setVisible(true);
        }
    }
}
