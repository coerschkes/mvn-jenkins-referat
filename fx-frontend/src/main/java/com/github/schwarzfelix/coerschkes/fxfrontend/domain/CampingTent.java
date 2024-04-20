package com.github.schwarzfelix.coerschkes.fxfrontend.domain;

public record CampingTent(long id, String name, String description, String price, String size, boolean hasInnerTent,
                          String manufacturer, String color, int personCount, String weight, String material, int stock,
                          String pictureBase64) {
}
