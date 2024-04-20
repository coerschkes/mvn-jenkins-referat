package com.github.schwarzfelix.coerschkes.fxfrontend.scene;

import com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure.TentRestRepository;
import javafx.fxml.FXMLLoader;

public abstract class BaseController {
    private static final String BASE_URL = "http://localhost:8080";
    protected final TentRestRepository repository = new TentRestRepository(BASE_URL);

    protected <T extends BaseController> T getController(final Class<T> clazz) {
        return ((clazz.cast(getLoader(clazz).getController())));
    }

    protected static FXMLLoader getLoader(final Class<? extends BaseController> clazz) {
        return FXMLLoaderFactory.getLoaderForClass(clazz);
    }
}
