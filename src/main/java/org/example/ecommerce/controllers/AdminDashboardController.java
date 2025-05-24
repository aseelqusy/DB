package org.example.ecommerce.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.example.ecommerce.utils.DBConnection;

public class AdminDashboardController {

    @FXML
    private void openManageProducts() {
        loadPage("/views/ManageProducts.fxml", "Manage Products");
    }

    @FXML
    private void openManageUsers() {
        loadPage("/views/ManageUsers.fxml", "Manage Users");
    }

    @FXML
    private void openOrders() {
        loadPage("/views/ManageOrders.fxml", "Manage Orders");
    }

    @FXML
    private void openCartItems() {
        loadPage("/views/ManageCart.fxml", "Manage Cart Items");
    }

    @FXML
    private void openReports() {
        loadPage("/views/Reports.fxml", "Sales Reports");
    }

    @FXML
    private void openHomePage() {
        navigate("/views/home.fxml", "Home");
    }

    @FXML
    private void openProductsPage() {
        navigate("/views/products.fxml", "Products");
    }

    @FXML
    private void openProfilePage() {
        navigate("/views/profile.fxml", "My Profile");
    }

    @FXML
    private void handleLogout() {
        navigate("/views/login.fxml", "Login");
    }

    private void loadPage(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = (Stage) javafx.stage.Window.getWindows().filtered(w -> w.isShowing()).get(0);
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void navigate(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            if (path.contains("home")) {
                scene.getStylesheets().add(getClass().getResource("/styles/home.css").toExternalForm());
            } else if (path.contains("products")) {
                scene.getStylesheets().add(getClass().getResource("/styles/products.css").toExternalForm());
            } else if (path.contains("admin_dashboard")) {
                scene.getStylesheets().add(getClass().getResource("/styles/admin.css").toExternalForm());
            }

            Stage stage = (Stage) javafx.stage.Window.getWindows().filtered(w -> w.isShowing()).get(0);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void generateReport() {
        try {

            Connection conn = DBConnection.getConnection();

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("title", "All Orders Report");

            InputStream jasperStream = getClass().getResourceAsStream("/reports/order_report.jasper");

            JasperPrint print;

            if (jasperStream != null) {
                print = JasperFillManager.fillReport(jasperStream, parameters, conn);
            } else {
                InputStream jrxmlStream = getClass().getResourceAsStream("/reports/order_report.jrxml");

                if (jrxmlStream == null) {
                    showAlert("Error", "Report file (.jrxml or .jasper) not found in /reports/");
                    return;
                }

                JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);
                print = JasperFillManager.fillReport(jasperReport, parameters, conn);
            }

            JasperViewer.viewReport(print, false);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to generate report:\n" + e.getMessage());
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
