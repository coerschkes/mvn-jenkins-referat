package com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure;

public record CampingTent(long id, String name, String description, String price, String size, int stock, int persons,
                          String pictureBase64) {
}
