package com.github.schwarzfelix.coerschkes.fxfrontend.scene;

import javafx.fxml.FXMLLoader;

public abstract class BaseController {
    protected <T extends BaseController> T getController(final Class<T> clazz) {
        return ((clazz.cast(getLoader(clazz).getController())));
    }

    protected static FXMLLoader getLoader(final Class<? extends BaseController> clazz) {
        return FXMLLoaderFactory.getLoaderForClass(clazz);
    }
}
