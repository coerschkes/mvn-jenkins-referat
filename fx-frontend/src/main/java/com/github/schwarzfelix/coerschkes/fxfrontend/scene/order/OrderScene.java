package com.github.schwarzfelix.coerschkes.fxfrontend.scene.order;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseScene;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.FXMLLoaderFactory;

import java.io.IOException;

public class OrderScene extends BaseScene {
    public OrderScene() throws IOException {
        super(FXMLLoaderFactory.ODER_SCENE_LOADER.load(), 200, 200);
    }
}
