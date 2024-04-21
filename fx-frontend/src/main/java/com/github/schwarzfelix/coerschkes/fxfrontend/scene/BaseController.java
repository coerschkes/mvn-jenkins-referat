package com.github.schwarzfelix.coerschkes.fxfrontend.scene;

import com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure.TentRestRepository;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop.ForcedReloadException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    /**
     * Resizes the given {@link Stage} to match the height and width of its underlying {@link javafx.scene.Scene}.
     *
     * @param stage - the stage to be resized
     */
    protected void resizeStageToScene(final Stage stage) {
        this.registerWindowResizeTrigger(stage);
        final PauseTransition wait = new PauseTransition(Duration.seconds(0.0001));
        wait.setOnFinished((e) -> {
            stage.setWidth(stage.getScene().getWidth());
            stage.setHeight(stage.getScene().getHeight());
        });
        wait.play();
    }

    /**
     * The window size is not updated properly. The exception is intended to trigger window resize.
     *
     * @param stage - stage to be triggered
     * @throws ForcedReloadException - Ignored {@link RuntimeException}
     */
    private void registerWindowResizeTrigger(final Stage stage) {
        stage.setOnShown(s -> {
            throw new ForcedReloadException();
        });
    }

    protected abstract void closeStage();
}

