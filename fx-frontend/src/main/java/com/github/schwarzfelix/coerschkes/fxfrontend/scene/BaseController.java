package com.github.schwarzfelix.coerschkes.fxfrontend.scene;

import com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure.TentRestRepository;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class BaseController {
    private static final String BASE_URL = "http://localhost:8080";
    protected final TentRestRepository repository = new TentRestRepository(BASE_URL);

    protected <T extends BaseController> T getController(final Class<T> clazz) {
        return ((clazz.cast(getLoader(clazz).getController())));
    }

    protected static FXMLLoader getLoader(final Class<? extends BaseController> clazz) {
        return FXMLLoaderFactory.getLoaderForClass(clazz);
    }

    protected void resizeStageToScene(final Stage stage, final Scene scene) {
        final PauseTransition wait = new PauseTransition(Duration.seconds(0.0001));
        wait.setOnFinished((e) -> {
            stage.setWidth(scene.getWidth());
            stage.setHeight(scene.getHeight());
        });
        wait.play();
    }

    protected abstract void closeStage();
}

