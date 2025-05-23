package org.example.ecommerce.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.ecommerce.utils.Session;



import java.io.IOException;
import java.util.Objects;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class CheckoutController {

    // Top bar
    @FXML private TextField searchField;
    @FXML private ImageView userAvatar;

    // Payment form fields
    @FXML private TextField cardholderName;
    @FXML private TextField cardNumber;
    @FXML private TextField expDate;
    @FXML private TextField cvv;
    @FXML private CheckBox sameAsBilling;

    @FXML private Button backButton;
    @FXML private Button payButton;

    // Summary labels (optional binding for logic)
    @FXML private Label subtotalLabel;
    @FXML private Label taxLabel;
    @FXML private Label shippingLabel;
    @FXML private Label totalLabel;



    // Example of action handler
    @FXML
    private void initialize() {
        // Initialization logic here if needed
    }

    @FXML
    private void handlePay() {
        System.out.println("Processing payment...");
        // Implement your payment logic here
    }

    @FXML
    private void handleBack() {
        System.out.println("Going back...");
        // Navigation logic here
    }

    public void handleLogout(ActionEvent actionEvent) {
    }

    public void goToBlog(ActionEvent actionEvent) {
    }



    public void goToContact(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/products.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToAbout(ActionEvent actionEvent) {
    }

    public void goToHome(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/home.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void goToCart(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/Cart.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
