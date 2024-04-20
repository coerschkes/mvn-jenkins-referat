package com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop;

import javafx.beans.property.SimpleStringProperty;

public final class CampingTentRow {
    private final SimpleStringProperty name;
    private final SimpleStringProperty size;
    private final SimpleStringProperty persons;
    private final SimpleStringProperty price;
    private final SimpleStringProperty stock;

    public CampingTentRow(String name, String size, String persons, String price, String stock) {
        this.name = new SimpleStringProperty(name);
        this.size = new SimpleStringProperty(size);
        this.persons = new SimpleStringProperty(persons);
        this.price = new SimpleStringProperty(price);
        this.stock = new SimpleStringProperty(stock);
    }

    public String getName() {
        return name.get();
    }

    public String getSize() {
        return size.get();
    }

    public String getPersons() {
        return persons.get();
    }

    public String getPrice() {
        return price.get();
    }

    public String getStock() {
        return stock.get();
    }
}
