package com.github.schwarzfelix.coerschkes.fxfrontend.scene.shop;

import com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure.CampingTent;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public final class CampingTentRow {
    private final SimpleStringProperty name;
    private final SimpleStringProperty size;
    private final SimpleIntegerProperty persons;
    private final SimpleStringProperty price;
    private final SimpleIntegerProperty stock;

    public CampingTentRow(String name, String size, int persons, String price, int stock) {
        this.name = new SimpleStringProperty(name);
        this.size = new SimpleStringProperty(size);
        this.persons = new SimpleIntegerProperty(persons);
        this.price = new SimpleStringProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
    }

    public static CampingTentRow of(final CampingTent tent) {
        return new CampingTentRow(tent.name(), tent.size(), tent.persons(), tent.price(), tent.stock());
    }

    public String getName() {
        return name.get();
    }

    public String getSize() {
        return size.get();
    }

    public int getPersons() {
        return persons.get();
    }

    public String getPrice() {
        return price.get();
    }

    public int getStock() {
        return stock.get();
    }
}
