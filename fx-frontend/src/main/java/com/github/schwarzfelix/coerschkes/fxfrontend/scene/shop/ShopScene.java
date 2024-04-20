package com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop;

import com.github.schwarzfelix.coerschkes.fxfrontend.TentApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ShopScene extends Scene {
    public ShopScene() throws IOException {
        super(getLoader().load(), 600, 500);
    }

    private static FXMLLoader getLoader() {
        return new FXMLLoader(TentApplication.class.getResource("shop.fxml"));
    }
}
