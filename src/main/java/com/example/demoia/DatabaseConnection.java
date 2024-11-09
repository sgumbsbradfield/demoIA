package com.example.demoia;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://bc-ws-amp-01:3306/sgumbsdb_"; // Replace with your DB URL
    private static final String USER = "SGumbs"; // Replace with your DB username
    private static final String PASSWORD = "iamstumped"; // Replace with your DB password
    private static Connection connection = null;

    // Method to initialize the connection when the application starts
    public static void initialize() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            showConnectionError();
            e.printStackTrace();
        }
    }

    // Getter for the connection
    public static Connection getConnection() {
        return connection;
    }

    // Method to display a connection error alert
    private static void showConnectionError() {
        Alert alert = new Alert(Alert.AlertType.ERROR,"", ButtonType.OK);
        alert.setTitle("Database Connection Error");
        alert.setHeaderText("Cannot connect to the database");
        alert.setContentText("Please check your connection settings or contact support.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit();  // Terminates the application
                System.exit(0);    // Ensures the JVM exits (useful if Platform.exit alone doesn't suffice)
            }
        });
    }
}
