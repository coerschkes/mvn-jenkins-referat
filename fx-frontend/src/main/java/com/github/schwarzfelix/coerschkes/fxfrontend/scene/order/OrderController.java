package com.github.schwarzfelix.coerschkes.fxfrontend.scene.order;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseController;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OrderController extends BaseController {
    public Label labelCongratulations;

    public void onButtonOkClicked() {
        //todo: refresh shop
        final Stage stage = (Stage) labelCongratulations.getScene().getWindow();
        stage.close();
    }

    public void setContent(final String message) {
        this.labelCongratulations.setText(message);
    }
}
