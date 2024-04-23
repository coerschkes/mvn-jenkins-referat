package com.github.schwarzfelix.coerschkes.fxfrontend.scene.details;

import com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure.CampingTent;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.order.OrderController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

/**
 * Controller for the details-scene. Creates the order-scene.
 */
public class DetailsController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(DetailsController.class.getName());
    private Stage orderStage;
    private long tentId;

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
    @FXML
    public Button orderButton;

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
        this.repository.orderTent(this.tentId);
        this.closeStage();
        this.showOrderComplete();
    }

    @FXML
    public void onButtonCancelClicked() {
        this.closeStage();
    }

    /**
     * Set the content of the details-scene.
     *
     * @param campingTent - content carrier
     */
    public void setContent(final CampingTent campingTent) {
        this.tentId = campingTent.id();
        this.tentImage.setImage(new Image(decodeImage(campingTent.pictureBase64())));
        this.labelName.setText(campingTent.name());
        this.textDescription.setText(campingTent.description());
        this.labelPrice.setText(campingTent.price());
    }

    public void disableOrder() {
        this.orderButton.setDisable(true);
    }

    @Override
    protected void closeStage() {
        ((Stage) this.tentImage.getScene().getWindow()).close();
    }

    private void showOrderComplete() {
        getController(OrderController.class).setContent("Congratulations!\n\nYour order of '" + this.labelName.getText() + "' is complete.");
        this.orderStage.show();
    }

    private void initializeOrderStage() {
        try {
            this.orderStage = new Stage();
            this.orderStage.setTitle("Order complete");
            this.orderStage.setScene(new Scene(getLoader(OrderController.class).load(), 400, 220));
            this.orderStage.setMinWidth(400);
            this.orderStage.setMinHeight(220);
        } catch (IOException e) {
            LOGGER.warning("Could not initialize order stage");
        }
    }

    private static ByteArrayInputStream decodeImage(final String base64String) {
        final byte[] decodedImage = Base64.getDecoder().decode(base64String);
        return new ByteArrayInputStream(decodedImage);
    }
}
