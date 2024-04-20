package com.github.schwarzfelix.coerschkes.resourceserver.domain.entity;

public record CampingTent(long id, String name, String description, String price, String size, int stock, int persons,
                          String pictureBase64) {
}

