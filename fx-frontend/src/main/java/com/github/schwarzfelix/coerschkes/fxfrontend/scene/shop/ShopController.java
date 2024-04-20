package com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop;

import com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure.CampingTent;
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
import java.util.function.Consumer;
import java.util.logging.Logger;

public class ShopController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(ShopController.class.getName());
    private Stage detailsStage;

    @FXML
    private TableView<CampingTentRow> tableStock;

    public void initialize() {
        this.tableStock.getItems().clear();
        this.repository.getAllTents(refreshStockTable());
    }

    @FXML
    public void onButtonDetailsClicked() {
        if (this.detailsStage == null) {
            initializeDetailsStage();
        }
        final CampingTentDetails campingTentDetails = new CampingTentDetails("1", this.getTestEncodedImg(), "asjkdlg", "sajkslgd", "asgjkl");
        this.getController(DetailsController.class).setContent(campingTentDetails);
        this.detailsStage.show();
    }

    private Consumer<List<CampingTent>> refreshStockTable() {
        return tents -> tableStock.setItems(FXCollections.observableList(tents
                .stream()
                .map(CampingTentRow::of)
                .toList()));
    }

    private void initializeDetailsStage() {
        try {
            this.detailsStage = new Stage();
            this.detailsStage.setTitle("Details");
            this.detailsStage.setScene(new Scene(getLoader(DetailsController.class).load(), 600, 500));
            this.detailsStage.setOnShown(s -> {
                throw new ForcedReloadException();
            });
            resizeStageToScene(detailsStage, detailsStage.getScene());
        } catch (IOException e) {
            LOGGER.warning("Could not initialize details stage");
        }
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