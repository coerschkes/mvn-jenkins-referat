package com.github.schwarzfelix.coerschkes.fxfrontend.scene.details;

import com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure.CampingTent;

public record CampingTentDetails(String imageBase64, String name, String description, String price) {

    public static CampingTentDetails of(CampingTent campingTent) {
        return new CampingTentDetails(campingTent.pictureBase64(), campingTent.name(), campingTent.description(), campingTent.price());
    }
}
