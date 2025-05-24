package org.example.ecommerce.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.ecommerce.models.CartItem;
import org.example.ecommerce.models.User;
import org.example.ecommerce.services.CartService;
import org.example.ecommerce.services.OrderService;
import org.example.ecommerce.utils.Session;

import java.io.IOException;
import java.util.List;

public class PaymentController {

    @FXML private TextField bonusCardField;
    @FXML private TextField discountField;
    @FXML private Button payBtn;

    private final CartService cartService = new CartService();
    private final OrderService orderService = new OrderService();

    @FXML
    private void initialize() {
        payBtn.setOnAction(event -> processPayment());
    }

    private void processPayment() {
        User currentUser = Session.getCurrentUser();
        if (currentUser == null) {
            showAlert("Error", "User session not found. Please log in again.");
            return;
        }

        int userId = currentUser.getId();
        List<CartItem> cartItems = cartService.getCartByUser(userId);

        if (cartItems.isEmpty()) {
            showAlert("Cart Empty", "Your cart is empty. Add items before checkout.");
            return;
        }

        double subtotal = cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        double tax = subtotal * 0.15; // 15% VAT
        double shipping = 30.0;       // fixed shipping
        double total = subtotal + tax + shipping;

        int orderId = orderService.createOrder(userId, total, cartItems);

        if (orderId != -1) {
            cartService.clearCart(userId);
            showAlert("Success", "Order #" + orderId + " completed successfully!");
            goToHome();
        } else {
            showAlert("Error", "Failed to place the order.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goToHome() {
        navigateTo("/views/home.fxml", "Home");
    }

    @FXML
    private void goToProducts() {
        navigateTo("/views/products.fxml", "Products");
    }

    @FXML
    private void goToCart() {
        navigateTo("/views/Cart.fxml", "Your Cart");
    }

    @FXML
    private void handleLogout() {
        Session.clear();
        navigateTo("/views/login.fxml", "Login");
    }

    private void navigateTo(String path, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = (Stage) payBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Failed to load: " + title);
        }
    }

    @FXML
    private void goToShipping() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/shipping.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) /* أي Node موجود في الصفحة */ payBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Shipping");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
