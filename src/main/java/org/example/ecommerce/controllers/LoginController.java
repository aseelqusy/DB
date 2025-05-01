package org.example.ecommerce.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController {

    @FXML
    private ImageView decorativeImage;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void initialize() {
        try {
            Image img = new Image(getClass().getResource("/assets/Sign.jpg").toExternalForm());
            decorativeImage.setImage(img);
        } catch (Exception e) {
            System.out.println("Image failed to load!");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.equals("admin") && password.equals("admin123")) {
            showAlert("Login Successful", "Welcome, admin!");
        } else {
            showAlert("Login Failed", "Invalid email or password.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
