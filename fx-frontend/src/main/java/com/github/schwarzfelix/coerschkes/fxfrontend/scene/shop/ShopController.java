package com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop;

import com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure.CampingTent;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.InMemoryCache;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.details.CampingTentDetails;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.details.DetailsController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Controller for the shop-scene (primary). Creates the details-scene.
 */
public class ShopController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(ShopController.class.getName());
    private final InMemoryCache cache = new InMemoryCache();
    private Stage detailsStage;

    @FXML
    private TableView<CampingTentRow> tableStock;

    public void initialize() {
        registerOnDoubleClickEvent();
        update();
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

    @FXML
    public void onButtonRefreshClicked() {
        update();
    }

    public void update() {
        this.repository.getAllTents(refreshStockTable());
    }

    @Override
    protected void closeStage() {
        throw new UnsupportedOperationException("Not supported cause this is the primary stage.");
    }

    /**
     * {@link Consumer} used as callback function to load the stock data async from the resource server and display it.
     *
     * @return callback function
     */
    private Consumer<List<CampingTent>> refreshStockTable() {
        return tents -> {
            this.cache.clear();
            this.tableStock.setItems(FXCollections.observableList(tents
                    .stream()
                    .peek(this.cache::add)
                    .map(CampingTentRow::of)
                    .toList()));
            this.tableStock.refresh();
        };
    }

    private void showDetailsStage(CampingTentRow selectedItem) {
        final CampingTent lookup = this.cache.lookup(selectedItem.getName());
        if (lookup.stock() == 0) {
            this.getController(DetailsController.class).disableOrder();
        }
        this.getController(DetailsController.class).setContent(CampingTentDetails.of(lookup));
        this.detailsStage.show();
    }

    private void initializeDetailsStage() {
        try {
            this.detailsStage = new Stage();
            this.detailsStage.setTitle("Details");
            this.detailsStage.setScene(new Scene(getLoader(DetailsController.class).load(), 930, 460));
            this.detailsStage.setMinWidth(930);
            this.detailsStage.setMinHeight(460);
        } catch (IOException e) {
            LOGGER.warning("Could not initialize details stage");
        }
    }

    /**
     * Registers an event listener for the double click event on {@link javafx.scene.control.TableRow}.
     */
    private void registerOnDoubleClickEvent() {
        tableStock.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2) {
                        onButtonDetailsClicked();
                    }
                }
        );
    }
}