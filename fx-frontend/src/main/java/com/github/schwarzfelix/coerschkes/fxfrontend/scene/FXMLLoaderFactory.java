package com.github.schwarzfelix.coerschkes.fxfrontend.scene;

import com.github.schwarzfelix.coerschkes.fxfrontend.scene.details.DetailsScene;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.order.OrderScene;
import com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop.ShopScene;
import javafx.fxml.FXMLLoader;

public class FXMLLoaderFactory {
    public static final FXMLLoader SHOP_SCENE_LOADER = new FXMLLoader(ShopScene.class.getResource("shop.fxml"));
    public static final FXMLLoader ODER_SCENE_LOADER = new FXMLLoader(OrderScene.class.getResource("order.fxml"));
    public static final FXMLLoader DETAILS_SCENE_LOADER = new FXMLLoader(DetailsScene.class.getResource("details.fxml"));
}
