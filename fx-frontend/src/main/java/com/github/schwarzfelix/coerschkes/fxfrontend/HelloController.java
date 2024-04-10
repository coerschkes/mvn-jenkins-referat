package com.github.schwarzfelix.coerschkes.fxfrontend;

import com.github.schwarzfelix.coerschkes.fxfrontend.connector.RestApiConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        RestApiConnector.callHello(response -> welcomeText.setText(response));
    }
}