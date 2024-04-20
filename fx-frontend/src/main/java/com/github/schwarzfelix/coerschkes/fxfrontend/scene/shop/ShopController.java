package com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.FXMLLoaderFactory;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.details.CampingTentDetails;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.details.DetailsController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.details.DetailsScene;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class ShopController extends BaseController {
    private Stage detailsStage;
    @FXML
    private TableView<CampingTentRow> tableStock;

    public void initialize() {
        tableStock.getItems().clear();
        tableStock.setItems(FXCollections.observableList(List.of(new CampingTentRow("test", "1,80m x 2,20m x 3,10m", "2", "699,44€", "300"))));
    }

    @FXML
    public void onButtonDetailsClicked() throws IOException {
        if (detailsStage == null) {
            detailsStage = new Stage();
            detailsStage.setTitle("Details");
            DetailsScene scene = new DetailsScene();
            detailsStage.setScene(scene);
            resizeStageToScene(detailsStage, scene);
        }
        ((DetailsController) FXMLLoaderFactory.DETAILS_SCENE_LOADER.getController()).setCampingTentDetails(new CampingTentDetails("1", this.getTestEncodedImg(), "asjkdlg", "sajkslgd", "asgjkl"));
        detailsStage.show();
    }

    @FXML
    public void onButtonOrderClicked() {

    }

    private String getTestEncodedImg() {
        try {
            return Base64.getEncoder().encodeToString(DetailsController.class.getResourceAsStream("test-img.jpg").readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}