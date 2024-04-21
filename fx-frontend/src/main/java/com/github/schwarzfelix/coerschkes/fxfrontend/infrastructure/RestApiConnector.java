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
    private static final HttpClient HTTPCLIENT = HttpClient.newHttpClient();
    private final String baseUrl;
    private final RequestBuilder requestBuilder;

    RestApiConnector(final String baseUrl) {
        this.baseUrl = baseUrl;
        this.requestBuilder = new RequestBuilder(baseUrl);
    }

    void getById(final long id, final Consumer<CampingTent> callback) {
        callGet("/tents/" + id, callback, CampingTentFactory::fromJson);
    }

    void getAll(final Consumer<List<CampingTent>> callback) {
        callGet("/tents", callback, CampingTentFactory::listFromJson);
    }

    void put(final long id) {
        callPut("/tents/" + id);
    }

    private <T> void callGet(final String path, final Consumer<T> callback, final Function<String, T> function) {
        callApi(requestBuilder.buildGetRequest(path), callback, function);
    }

    private <T> void callPut(final String path) {
        callApi(requestBuilder.buildPutRequest(path), o -> {}, s -> s);
    }

    private <T> void callApi(final HttpRequest request, final Consumer<T> callback, final Function<String, T> transformer) {
        sendAsyncRequest(request, (response, throwable) -> Platform.runLater(() -> {
            if (response == null) {
                throw new CommunicationFailureException("Unable to connect to the api on '" + baseUrl + "'.");
            }
            callback.accept(transformer.apply(response.body()));
        }));
    }

    private static void sendAsyncRequest(final HttpRequest request, final BiConsumer<HttpResponse<String>, Throwable> futureConsumer) {
        final var responseFuture = HTTPCLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        responseFuture.whenComplete(futureConsumer);
    }
}
