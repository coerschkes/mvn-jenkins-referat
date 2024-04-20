package com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure;

import javafx.application.Platform;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

class RestApiConnector {
    private static final String BASE_URI = "http://localhost:8080";
    private static final HttpClient HTTPCLIENT = HttpClient.newHttpClient();
    private static final RequestBuilder REQUEST_BUILDER = new RequestBuilder(BASE_URI);

    void get(final String path, final Consumer<CampingTent> callback) {
        callGet(path, callback, CampingTentFactory::fromJson);
    }

    void getCollection(final String path, final Consumer<List<CampingTent>> callback) {
        callGet(path, callback, CampingTentFactory::listFromJson);
    }

    void delete(final String path, final Consumer<String> callback) {

    }

    private <T> void callGet(final String path, final Consumer<T> callback, final Function<String, T> function) {
        final HttpRequest request = REQUEST_BUILDER.buildGetRequest(path);
        //todo: Handle NullPointerException when request does not succeed
        callApiAsync(request, (response, throwable) -> Platform.runLater(() -> callback.accept(function.apply((String) response.body()))));
    }

    private <T> void callDelete(final String path, final Consumer<String> callback) {
//        build remove request
        final HttpRequest request = REQUEST_BUILDER.buildDeleteRequest(path);
        //todo: Handle NullPointerException when request does not succeed
        callApiAsync(request, (response, throwable) -> Platform.runLater(() -> callback.accept((String) response.body())));
    }

    private static void callApiAsync(HttpRequest request, BiConsumer<HttpResponse<String>, Throwable> futureConsumer) {
        final var responseFuture = HTTPCLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        responseFuture.whenComplete(futureConsumer);
    }
}
