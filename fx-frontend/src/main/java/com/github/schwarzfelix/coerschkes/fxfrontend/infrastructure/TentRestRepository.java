package com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure;

import com.github.schwarzfelix.coerschkes.fxfrontend.domain.CampingTent;

import java.util.List;
import java.util.function.Consumer;

public class TentRestRepository {
    private static final RestApiConnector CONNECTOR = new RestApiConnector();

    public static void getTent(final int id, final Consumer<CampingTent> callback) {
        CONNECTOR.get("/tents/" + id, callback);
    }

    public static void getAllTents(final Consumer<List<CampingTent>> callback) {
        CONNECTOR.getCollection("/tents", callback);
    }

    public static void orderTent(final int id, final Consumer<CampingTent> callback) {

    }
}
