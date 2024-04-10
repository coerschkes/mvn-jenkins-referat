module com.github.schwarzfelix.coerschkes.fxfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens com.github.schwarzfelix.coerschkes.fxfrontend to javafx.fxml;
    exports com.github.schwarzfelix.coerschkes.fxfrontend;
    exports com.github.schwarzfelix.coerschkes.fxfrontend.connector;
    opens com.github.schwarzfelix.coerschkes.fxfrontend.connector to javafx.fxml;
}