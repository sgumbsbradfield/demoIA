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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mindrot.jbcrypt.BCrypt.*;


public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private TextField passwordTextField;
    @FXML
    private CheckBox showPasswordCheckBox;

    public void goTo(ActionEvent event) throws IOException {
        Button source = (Button) event.getSource();
        String pageName = source.getText().toLowerCase();
        String fxmlPage = pageName + "Page.fxml";

        Parent root = FXMLLoader.load(getClass().getResource(fxmlPage));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle(pageName.toUpperCase()+"...");
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

    public void login(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if fields are empty
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username or password cannot be empty.");
            return;
        }

        // Connect to the database and check if the username exists
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT password_hash FROM users WHERE username = ?")) {

            // Set username parameter in the SQL query
            statement.setString(1, username);

            // Execute the query and process the result
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Username exists, now check if the password matches
                String storedPasswordHash = resultSet.getString("password_hash");

                // Compare the input password with the stored password hash (hashing needed)
                if (checkPassword(password, storedPasswordHash)) {
                    // Passwords match; proceed with login
                    showAlert("Success", "Login successful.");
                } else {
                    // Passwords don't match
                    showAlert("Error", "Invalid password.");
                }
            } else {
                // Username does not exist
                showAlert("Error", "Username not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "An error occurred while accessing the database.");
        }
    }

    // Simulated password check (replace with actual hashing logic as per your security requirements)
    private boolean checkPassword(String inputPassword, String storedPasswordHash) {
        // TODO: Use an actual hashing library, like BCrypt, for secure password verification
        String combinedPassword = passwordField.getText() + usernameField.getText();
        String encrpytPassword = hashpw(storedPasswordHash, gensalt());
        System.out.println(encrpytPassword);
        return encrpytPassword.equals(storedPasswordHash);
    }

    // Utility method to show an alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
