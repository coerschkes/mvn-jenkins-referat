package com.github.schwarzfelix.coerschkes.fxfrontend.scene;

import com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure.CampingTent;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple cache to store the latest tent stock from the resource server.
 */
public class InMemoryCache {
    private final List<CampingTent> values = new ArrayList<>();

    public void add(final CampingTent value) {
        this.values.add(value);
    }

    public void clear() {
        this.values.clear();
    }

    public CampingTent lookup(final String name) {
        return this.values
                .stream()
                .filter(tent -> tent.name().equals(name))
                .findFirst()
                .orElseThrow();
    }
}
