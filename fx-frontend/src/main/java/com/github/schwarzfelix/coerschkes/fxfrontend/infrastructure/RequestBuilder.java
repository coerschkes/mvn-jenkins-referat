package com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

public class RequestBuilder {
    private final String baseUri;

    public RequestBuilder(String baseUri) {
        this.baseUri = baseUri;
    }

    HttpRequest buildDeleteRequest(final String path) {
        try {
            return buildSimpleRequest(path).DELETE().build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    HttpRequest buildGetRequest(final String path) {
        try {
            return buildSimpleRequest(path).GET().build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest.Builder buildSimpleRequest(String path) throws URISyntaxException {
        return HttpRequest.newBuilder(new URI(this.baseUri + path));
    }

}