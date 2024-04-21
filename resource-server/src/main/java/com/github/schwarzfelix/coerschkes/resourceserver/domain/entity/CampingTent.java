package com.github.schwarzfelix.coerschkes.resourceserver.domain.entity;

public record CampingTent(long id, String name, String description, String price, String size, int stock, int persons,
                          String pictureBase64) {
    public CampingTent withStock(final int stock) {
        return new CampingTent(this.id, this.name, this.description, this.price, this.size, stock, this.persons, this.pictureBase64);
    }
}

