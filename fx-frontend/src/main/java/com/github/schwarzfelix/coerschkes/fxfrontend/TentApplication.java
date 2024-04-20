package com.github.schwarzfelix.coerschkes.fxfrontend;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop.ShopScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TentApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final Scene scene = new ShopScene();
        stage.setTitle("Tent Shop");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}