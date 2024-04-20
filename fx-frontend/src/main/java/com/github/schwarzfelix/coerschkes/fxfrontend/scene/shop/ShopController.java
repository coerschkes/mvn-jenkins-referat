package com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.details.CampingTentDetails;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.details.DetailsController;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class ShopController extends BaseController {
    private Stage detailsStage;

    @FXML
    private TableView<CampingTentRow> tableStock;

    public void initialize() {
        tableStock.getItems().clear();
        //todo call api here async to load items
        tableStock.setItems(FXCollections.observableList(List.of(new CampingTentRow("test", "1,80m x 2,20m x 3,10m", 2, "699,44€", 300))));
    }

    @FXML
    public void onButtonDetailsClicked() throws IOException {
        if (detailsStage == null) {
            initializeDetailsStage();
        }
        final CampingTentDetails campingTentDetails = new CampingTentDetails("1", this.getTestEncodedImg(), "asjkdlg", "sajkslgd", "asgjkl");
        setContent(campingTentDetails);
        detailsStage.show();
    }

    private void setContent(final CampingTentDetails details) {
        this.getController(DetailsController.class).setContent(details);
    }

    private void initializeDetailsStage() throws IOException {
        this.detailsStage = new Stage();
        this.detailsStage.setTitle("Details");
        this.detailsStage.setScene(new Scene(getLoader(DetailsController.class).load(), 600, 500));
        this.detailsStage.setOnShown(s -> {
            throw new ForcedReloadException();
        });
        resizeStageToScene(detailsStage, detailsStage.getScene());
    }

    private String getTestEncodedImg() {
        try {
            return Base64.getEncoder().encodeToString(DetailsController.class.getResourceAsStream("test-img.jpg").readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void resizeStageToScene(final Stage stage, final Scene scene) {
        final PauseTransition wait = new PauseTransition(Duration.seconds(0.0001));
        wait.setOnFinished((e) -> {
            stage.setWidth(scene.getWidth());
            stage.setHeight(scene.getHeight());
        });
        wait.play();
    }
}