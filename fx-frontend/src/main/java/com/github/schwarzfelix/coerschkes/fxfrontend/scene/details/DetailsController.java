package com.github.schwarzfelix.coerschkes.fxfrontend.scene.details;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.order.OrderController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Logger;

public class DetailsController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(DetailsController.class.getName());
    private Stage orderStage;

    @FXML
    public ImageView tentImage;
    public Label labelName;
    public Label labelDescription;
    public Label labelPrice;

    //todo: remove when actual data incoming
    public void initialize() {
        this.tentImage.setImage(new Image(DetailsController.class.getResourceAsStream("test-img.jpg")));
    }

    @FXML
    public void onButtonOrderClicked() {
        if (this.orderStage == null) {
            initializeOrderStage();
        }
        //todo callback here for shop scene to refresh
        this.orderStage.setOnCloseRequest(e -> System.out.println("test"));
        getController(OrderController.class).setContent("congratulations! Order of " + this.labelName.getText() + " complete.");
        this.orderStage.show();
    }

    @FXML
    public void onButtonCancelClicked() {
        final Stage stage = (Stage) tentImage.getScene().getWindow();
        stage.close();
    }

    public void setContent(final CampingTentDetails campingTentDetails) {
        this.tentImage.setImage(new Image(decodeImage(campingTentDetails)));
        this.labelName.setText(campingTentDetails.name());
        this.labelDescription.setText(campingTentDetails.description());
        this.labelPrice.setText(campingTentDetails.price());
    }

    private void initializeOrderStage() {
        try {
            this.orderStage = new Stage();
            this.orderStage.setTitle("Order complete");
            this.orderStage.setScene(new Scene(getLoader(OrderController.class).load(), 200, 200));
        } catch (IOException e) {
            LOGGER.warning("Could not initialize order stage");
        }
    }

    private static ByteArrayInputStream decodeImage(CampingTentDetails campingTentDetails) {
        final byte[] decodedImage = Base64.getDecoder().decode(campingTentDetails.imageBase64());
        return new ByteArrayInputStream(decodedImage);
    }
}
