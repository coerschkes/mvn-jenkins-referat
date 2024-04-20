package com.github.schwarzfelix.coerschkes.fxfrontend.scene;

import javafx.scene.Parent;
import javafx.scene.Scene;

public abstract class BaseScene extends Scene {
    protected static final int HEIGHT = 500;
    protected static final int WIDTH = 600;

    public BaseScene(Parent parent, double v, double v1) {
        super(parent, v, v1);
    }

    public int getDefinedHeight() {
        return HEIGHT;
    }

    public int getDefinedWidth() {
        return WIDTH;
    }
}
