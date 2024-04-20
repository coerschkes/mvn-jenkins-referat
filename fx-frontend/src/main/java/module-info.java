module com.github.schwarzfelix.coerschkes.fxfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.github.schwarzfelix.coerschkes.fxfrontend to javafx.fxml;
    exports com.github.schwarzfelix.coerschkes.fxfrontend;
    exports com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure;
    exports com.github.schwarzfelix.coerschkes.fxfrontend.domain;
    opens com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure to javafx.fxml;
    exports com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop;
    opens com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop to javafx.fxml;
}