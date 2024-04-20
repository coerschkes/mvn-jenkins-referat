package com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.List;

public class ShopController {
    @FXML
    private TableView<CampingTentRow> tableStock;

    public void initialize() {
        tableStock.getItems().clear();
        tableStock.setItems(FXCollections.observableList(List.of(new CampingTentRow("test", "1,80m x 2,20m x 3,10m", "2", "699,44€", "300"))));
    }

    @FXML
    protected void onButtonDetailsClicked() {

    }

    @FXML
    public void onButtonOrderClicked() {

    }
}