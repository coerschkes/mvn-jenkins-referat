package com.github.schwarzfelix.coerschkes.fxfrontend.scene.order;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop.ShopController;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OrderController extends BaseController {
    @FXML
    public Text textCongratulations;

    public void onButtonOkClicked() {
        getController(ShopController.class).initialize();
        final Stage stage = (Stage) textCongratulations.getScene().getWindow();
        stage.close();
    }

    public void setContent(final String message) {
        this.textCongratulations.setText(message);
    }
}
