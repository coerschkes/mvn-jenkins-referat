package com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseScene;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.FXMLLoaderFactory;

import java.io.IOException;

public class ShopScene extends BaseScene {
    public ShopScene() throws IOException {
        super(FXMLLoaderFactory.SHOP_SCENE_LOADER.load(), 600, 500);
    }
}
