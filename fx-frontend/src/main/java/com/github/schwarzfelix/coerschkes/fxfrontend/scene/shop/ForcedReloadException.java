package com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop;

public class ForcedReloadException extends RuntimeException {
    public ForcedReloadException() {
        super("Forced reload - intended exception here");
    }
}
