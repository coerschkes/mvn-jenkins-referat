package com.github.schwarzfelix.coerschkes.fxfrontend;

import com.github.schwarzfelix.coerschkes.fxfrontend.connector.RestApiConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TentController {
    @FXML
    public TextField inputText;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onFetchAllButtonClick() {
        RestApiConnector.callGet("/tents", response -> welcomeText.setText(response.body()));
    }

    @FXML
    protected void onFetchByIdButtonClick() {
        if (!inputText.getText().isEmpty()) {
            RestApiConnector.callGet("/tents/" + inputText.getText(), response -> {
                welcomeText.setText(response.body());
            });
        }

    }

    @FXML
    protected void onDeleteByIdButtonClick() {
        if (!inputText.getText().isEmpty()) {
            RestApiConnector.callDelete("/tents/" + inputText.getText(), response -> {
                welcomeText.setText(String.valueOf(response.statusCode()));
            });
        }
    }
}