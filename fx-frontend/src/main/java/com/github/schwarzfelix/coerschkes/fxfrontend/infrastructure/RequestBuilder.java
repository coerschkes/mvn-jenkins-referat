package com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.function.Function;

public class RequestBuilder {
    private final String baseUri;

    public RequestBuilder(String baseUri) {
        this.baseUri = baseUri;
    }

    HttpRequest buildPutRequest(final String path) {
        return buildSimpleRequest(path, builder -> builder.PUT(HttpRequest.BodyPublishers.ofString("")));
    }

    HttpRequest buildGetRequest(final String path) {
        return buildSimpleRequest(path, HttpRequest.Builder::GET);
    }

    private HttpRequest buildSimpleRequest(final String path, final Function<HttpRequest.Builder, HttpRequest.Builder> httpMethodProvider) {
        try {
            return httpMethodProvider.apply(HttpRequest.newBuilder(new URI(this.baseUri + path))).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
