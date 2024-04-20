package com.github.schwarzfelix.coerschkes.fxfrontend.scene.order;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OrderController {
    public Label labelCongratulations;

    public void onButtonOkClicked() {
        //todo: refresh shop
        final Stage stage = (Stage) labelCongratulations.getScene().getWindow();
        stage.close();
    }

    public void setCongratulationsMessage(final String message) {
        this.labelCongratulations.setText(message);
    }
}
