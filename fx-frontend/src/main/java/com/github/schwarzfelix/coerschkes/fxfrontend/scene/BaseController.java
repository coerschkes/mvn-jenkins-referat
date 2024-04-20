package com.github.schwarzfelix.coerschkes.fxfrontend.scene;

import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class BaseController {
    protected void resizeStageToScene(final Stage stage, final BaseScene scene) {
        final PauseTransition wait = new PauseTransition(Duration.seconds(0.0001));
        wait.setOnFinished((e) -> {
            stage.setWidth(scene.getDefinedWidth());
            stage.setHeight(scene.getDefinedHeight());
        });
        wait.play();
    }
}
