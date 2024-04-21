package com.github.schwarzfelix.coerschkes.fxfrontend.scene.order;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop.ShopController;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller for the order-scene.
 */
public class OrderController extends BaseController {
    @FXML
    public Text textCongratulations;

    public void onButtonOkClicked() {
        this.getController(ShopController.class).update();
        this.closeStage();
    }

    /**
     * Set the content of the details-scene.
     *
     * @param message - content
     */
    public void setContent(final String message) {
        this.textCongratulations.setText(message);
    }

    @Override
    protected void closeStage() {
        ((Stage) textCongratulations.getScene().getWindow()).close();
    }
}
