package com.github.schwarzfelix.coerschkes.fxfrontend.scene;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.details.DetailsController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.order.OrderController;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop.ShopController;
import javafx.fxml.FXMLLoader;

public class FXMLLoaderFactory {
    private static final FXMLLoader SHOP_SCENE_LOADER = new FXMLLoader(ShopController.class.getResource("shop.fxml"));
    private static final FXMLLoader ODER_SCENE_LOADER = new FXMLLoader(OrderController.class.getResource("order.fxml"));
    private static final FXMLLoader DETAILS_SCENE_LOADER = new FXMLLoader(DetailsController.class.getResource("details.fxml"));

    public static FXMLLoader getLoaderForClass(Class<? extends BaseController> clazz) {
        if (clazz.equals(ShopController.class)) {
            return SHOP_SCENE_LOADER;
        } else if (clazz.equals(OrderController.class)) {
            return ODER_SCENE_LOADER;
        } else if (clazz.equals(DetailsController.class)) {
            return DETAILS_SCENE_LOADER;
        } else {
            throw new IllegalArgumentException("Unknown class " + clazz);
        }
    }
}
