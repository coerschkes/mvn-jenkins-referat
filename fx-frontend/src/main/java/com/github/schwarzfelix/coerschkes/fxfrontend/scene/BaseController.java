package com.github.schwarzfelix.coerschkes.fxfrontend.scene;

import com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure.TentRestRepository;
import javafx.fxml.FXMLLoader;

/**
 * Base controller encapsulating common controller logic.
 */
public abstract class BaseController {
    private static final String BASE_URL = "http://localhost:8080";
    protected final TentRestRepository repository = new TentRestRepository(BASE_URL);

    /**
     * Loads the {@link FXMLLoader} for a specific controller class and retrieves the controller instance.
     *
     * @param clazz - Controller class
     * @param <T>   type of the controller
     * @return Controller of type T
     */
    protected <T extends BaseController> T getController(final Class<T> clazz) {
        return ((clazz.cast(getLoader(clazz).getController())));
    }

    /**
     * Loads the instance of a {@link FXMLLoader} for a specific controller.
     *
     * @param clazz - Controller class
     * @return the {@link FXMLLoader} instance
     */
    protected static FXMLLoader getLoader(final Class<? extends BaseController> clazz) {
        return FXMLLoaderMapper.getLoaderForClass(clazz);
    }

    protected abstract void closeStage();
}

