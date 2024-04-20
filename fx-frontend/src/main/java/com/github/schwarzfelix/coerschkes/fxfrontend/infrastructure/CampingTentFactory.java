package com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure;

import com.github.schwarzfelix.coerschkes.fxfrontend.domain.CampingTent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

class CampingTentFactory {
    private static final Gson GSON = new Gson();

    static CampingTent fromJson(final String json) {
        return GSON.fromJson(json, CampingTent.class);
    }

    static List<CampingTent> listFromJson(final String json) {
        final TypeToken<List<CampingTent>> typeToken = new TypeToken<>() {};
        return GSON.fromJson(json, typeToken.getType());
    }
}
