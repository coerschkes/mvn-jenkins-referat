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

public class DetailsController extends BaseController {
    private Stage orderStage;

    @FXML
    public ImageView tentImage;
    public Label labelName;
    public Label labelDescription;
    public Label labelPrice;

    public void initialize() {
        this.tentImage.setImage(new Image(DetailsController.class.getResourceAsStream("test-img.jpg")));
    }

    @FXML
    public void onButtonOrderClicked() throws IOException {
        if (orderStage == null) {
            initializeOrderStage();
        }
        //todo callback here for shop scene to refresh
        this.orderStage.setOnCloseRequest(e -> System.out.println("test"));
        getController(OrderController.class).setContent("congratulations! Order of " + labelName.getText() + " complete.");
        orderStage.show();
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

    private void initializeOrderStage() throws IOException {
        this.orderStage = new Stage();
        this.orderStage.setTitle("Order complete");
        this.orderStage.setScene(new Scene(getLoader(OrderController.class).load(), 200, 200));
    }

    private static ByteArrayInputStream decodeImage(CampingTentDetails campingTentDetails) {
        byte[] decodedImage = Base64.getDecoder().decode(campingTentDetails.imageBase64());
        return new ByteArrayInputStream(decodedImage);
    }
}
