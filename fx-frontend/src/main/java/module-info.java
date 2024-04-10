module com.github.schwarzfelix.coerschkes.fxfrontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.github.schwarzfelix.coerschkes.fxfrontend to javafx.fxml;
    exports com.github.schwarzfelix.coerschkes.fxfrontend;
}