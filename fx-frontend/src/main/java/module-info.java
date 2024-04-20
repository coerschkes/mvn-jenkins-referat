module com.github.schwarzfelix.coerschkes.fxfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.github.schwarzfelix.coerschkes.fxfrontend to javafx.fxml;
    exports com.github.schwarzfelix.coerschkes.fxfrontend;
    exports com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure;
    opens com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure to javafx.fxml;
    exports com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop;
    exports com.github.schwarzfelix.coerschkes.fxfrontend.scene.details;
    exports com.github.schwarzfelix.coerschkes.fxfrontend.scene.order;
    opens com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop to javafx.fxml;
}