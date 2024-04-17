package com.github.schwarzfelix.coerschkes.fxfrontend.connector;

import javafx.application.Platform;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class RestApiConnector {
    private static final String BASE_URI = "http://localhost:8080";
    private static final HttpClient HTTPCLIENT = HttpClient.newHttpClient();

    public static void callGet(final String path, final Consumer<HttpResponse<String>> callback) {
        final HttpRequest request = buildGetRequest(path);
        callRestApi(request, HttpResponse.BodyHandlers.ofString(), callback);
    }

    public static void callDelete(final String path, final Consumer<HttpResponse<String>> callback) {
        final HttpRequest request = buildDeleteRequest(path);
        callRestApi(request, HttpResponse.BodyHandlers.ofString(), callback);
    }

    private static HttpRequest buildGetRequest(final String path) {
        try {
            return simpleRequestBuilder(path).GET().build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpRequest buildDeleteRequest(final String path) {
        try {
            return simpleRequestBuilder(path).DELETE().build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpRequest.Builder simpleRequestBuilder(String path) throws URISyntaxException {
        return HttpRequest.newBuilder(new URI(BASE_URI + path));
    }

    private static <T> void callRestApi(final HttpRequest request,
                                        final HttpResponse.BodyHandler<T> bodyHandler,
                                        final Consumer<HttpResponse<T>> responseConsumer) {
        final CompletableFuture<HttpResponse<T>> responseFuture = HTTPCLIENT.sendAsync(request, bodyHandler);
        //todo: Handle NullPointerException when request does not succeed
        responseFuture.whenComplete((response, throwable) -> Platform.runLater(() -> responseConsumer.accept(response)));
    }
}
