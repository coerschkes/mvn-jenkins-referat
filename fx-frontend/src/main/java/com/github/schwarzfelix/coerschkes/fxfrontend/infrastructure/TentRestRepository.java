package com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure;

import java.util.List;
import java.util.function.Consumer;

public class TentRestRepository {
    private final RestApiConnector connector;

    public TentRestRepository(final String baseUrl) {
        this.connector = new RestApiConnector(baseUrl);
    }

    public void getAllTents(final Consumer<List<CampingTent>> callback) {
        this.connector.callGet(callback, JsonListTransformer::fromJson);
    }

    public void orderTent(final long id) {
        this.connector.callPut(id);
    }
}
