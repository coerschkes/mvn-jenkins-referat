package com.github.schwarzfelix.coerschkes.fxfrontend.scene.details;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.FXMLLoaderFactory;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.order.OrderController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.order.OrderScene;
import javafx.fxml.FXML;
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
            this.orderStage = new Stage();
            orderStage.setTitle("Order complete");
            OrderScene scene = new OrderScene();
            ((OrderController) FXMLLoaderFactory.ODER_SCENE_LOADER.getController()).setCongratulationsMessage("congratulations! Order of " + labelName.getText() + " complete.");
            orderStage.setScene(scene);
            //todo callback here for shop scene to refresh
            orderStage.setOnCloseRequest(e -> System.out.println("test"));
        }
        orderStage.show();
    }

    @FXML
    public void onButtonCancelClicked() {
        final Stage stage = (Stage) tentImage.getScene().getWindow();
        stage.close();
    }

    public void setCampingTentDetails(final CampingTentDetails campingTentDetails) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(campingTentDetails.imageBase64());
        this.tentImage.setImage(new Image(new ByteArrayInputStream(decode)));
        this.labelName.setText(campingTentDetails.name());
        this.labelDescription.setText(campingTentDetails.description());
        this.labelPrice.setText(campingTentDetails.price());
    }
}
