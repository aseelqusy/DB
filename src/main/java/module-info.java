module org.example.ecommerce {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.ecommerce.app to javafx.graphics, javafx.fxml;
    opens org.example.ecommerce.controllers to javafx.fxml;

    exports org.example.ecommerce.app;
    exports org.example.ecommerce.controllers;
}
