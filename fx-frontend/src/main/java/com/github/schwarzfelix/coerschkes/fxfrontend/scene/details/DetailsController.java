package com.github.schwarzfelix.coerschkes.fxfrontend.scene.details;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.order.OrderController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop.ForcedReloadException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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
    @FXML
    public Label labelName;
    @FXML
    public Text textDescription;
    @FXML
    public Label labelPrice;
    @FXML
    public ScrollPane scrollPaneDescription;

    public void initialize() {
        scrollPaneDescription.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneDescription.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneDescription.setStyle("-fx-background-color:transparent;");
    }

    @FXML
    public void onButtonOrderClicked() {
        if (this.orderStage == null) {
            initializeOrderStage();
        }
        //todo callback here for shop scene to refresh
        this.orderStage.setOnCloseRequest(e -> System.out.println("test"));
        getController(OrderController.class).setContent("Congratulations!\n\nYour order of '" + this.labelName.getText() + "' is complete.");
        ((Stage) this.tentImage.getScene().getWindow()).close();
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
        this.textDescription.setText(campingTentDetails.description());
        this.labelPrice.setText(campingTentDetails.price());
    }

    private void initializeOrderStage() {
        try {
            this.orderStage = new Stage();
            this.orderStage.setTitle("Order complete");
            this.orderStage.setScene(new Scene(getLoader(OrderController.class).load(), 400, 220));
            this.orderStage.setOnShown(s -> {
                throw new ForcedReloadException();
            });
            resizeStageToScene(orderStage, orderStage.getScene());
        } catch (IOException e) {
            LOGGER.warning("Could not initialize order stage");
        }
    }

    private static ByteArrayInputStream decodeImage(CampingTentDetails campingTentDetails) {
        final byte[] decodedImage = Base64.getDecoder().decode(campingTentDetails.imageBase64());
        return new ByteArrayInputStream(decodedImage);
    }
}
