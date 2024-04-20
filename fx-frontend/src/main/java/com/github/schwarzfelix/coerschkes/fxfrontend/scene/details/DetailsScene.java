package com.github.schwarzfelix.coerschkes.fxfrontend.scene.details;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.BaseScene;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.FXMLLoaderFactory;

import java.io.IOException;

public class DetailsScene extends BaseScene {
    public DetailsScene() throws IOException {
        super(FXMLLoaderFactory.DETAILS_SCENE_LOADER.load(), HEIGHT, WIDTH);
    }
}