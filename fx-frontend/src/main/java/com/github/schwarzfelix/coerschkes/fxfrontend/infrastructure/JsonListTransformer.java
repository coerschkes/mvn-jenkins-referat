package com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JsonListTransformer {
    private static final Gson GSON = new Gson();

    public static List<CampingTent> fromJson(final String json) {
        final TypeToken<List<CampingTent>> typeToken = new TypeToken<>() {
        };
        return GSON.fromJson(json, typeToken.getType());
    }
}
