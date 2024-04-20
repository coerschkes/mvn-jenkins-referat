package com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop;

import com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure.CampingTent;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.InMemoryCache;
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
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class ShopController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(ShopController.class.getName());
    private final InMemoryCache cache = new InMemoryCache();
    private Stage detailsStage;

    @FXML
    private TableView<CampingTentRow> tableStock;

    public void initialize() {
        this.repository.getAllTents(refreshStockTable());
    }

    private Consumer<List<CampingTent>> refreshStockTable() {
        return tents -> {
            this.tableStock.getItems().clear();
            this.cache.clear();
            tableStock.setItems(FXCollections.observableList(tents
                    .stream()
                    .peek(this.cache::add)
                    .map(CampingTentRow::of)
                    .toList()));
        };
    }

    @FXML
    public void onButtonDetailsClicked() {
        if (this.detailsStage == null) {
            initializeDetailsStage();
        }
        final CampingTentRow selectedItem = this.tableStock.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            showDetailsStage(selectedItem);
        }
    }

    private void showDetailsStage(CampingTentRow selectedItem) {
        final CampingTent lookup = this.cache.lookup(selectedItem.getName());
        this.getController(DetailsController.class).setContent(CampingTentDetails.of(lookup));
        this.detailsStage.show();
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

    private void resizeStageToScene(final Stage stage, final Scene scene) {
        final PauseTransition wait = new PauseTransition(Duration.seconds(0.0001));
        wait.setOnFinished((e) -> {
            stage.setWidth(scene.getWidth());
            stage.setHeight(scene.getHeight());
        });
        wait.play();
    }
}