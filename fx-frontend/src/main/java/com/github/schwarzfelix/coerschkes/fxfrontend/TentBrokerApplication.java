package com.github.schwarzfelix.coerschkes.fxfrontend;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.FXMLLoaderMapper;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop.ShopController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TentBrokerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final Scene scene = new Scene(FXMLLoaderMapper.getLoaderForClass(ShopController.class).load(), 600, 500);
        stage.setTitle("Tent Broker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}