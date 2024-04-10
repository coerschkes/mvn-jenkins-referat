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

    public static void callHello(final Consumer<String> callback) {
        final HttpRequest request = buildGetRequest("/hello");
        callRestApi(request, HttpResponse.BodyHandlers.ofString(), callback);
    }

    private static HttpRequest buildGetRequest(final String path) {
        try {
            return getRequestBuilder(path).GET().build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpRequest buildPostRequest(final String path, final HttpRequest.BodyPublisher bodyPublisher) {
        try {
            return getRequestBuilder(path).POST(bodyPublisher).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpRequest.Builder getRequestBuilder(String path) throws URISyntaxException {
        return HttpRequest.newBuilder(new URI(BASE_URI + path));
    }

    private static <T> void callRestApi(final HttpRequest request,
                                        final HttpResponse.BodyHandler<T> bodyHandler,
                                        final Consumer<T> responseConsumer) {
        CompletableFuture<HttpResponse<T>> responseFuture = HTTPCLIENT.sendAsync(request, bodyHandler);
        responseFuture.whenComplete((response, throwable) -> Platform.runLater(() -> responseConsumer.accept(response.body())));
    }
}
