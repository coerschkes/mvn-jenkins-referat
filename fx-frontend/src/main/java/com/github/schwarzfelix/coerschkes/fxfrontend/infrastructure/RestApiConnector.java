package com.github.schwarzfelix.coerschkes.fxfrontend.infrastructure;

import javafx.application.Platform;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class RestApiConnector {
    private static final HttpClient HTTPCLIENT = HttpClient.newHttpClient();
    private static final String RESOURCE_PATH = "/tents";
    private final String baseUrl;
    private final RequestBuilder requestBuilder;

    public RestApiConnector(final String baseUrl) {
        this.baseUrl = baseUrl;
        this.requestBuilder = new RequestBuilder(baseUrl);
    }

    public void callGet(final Consumer<List<CampingTent>> callback, final Function<String, List<CampingTent>> bodyTransformer) {
        sendAsyncRequest(requestBuilder.buildGetRequest(RESOURCE_PATH), (response, ignored) -> Platform.runLater(() -> {
            if (response == null) {
                throw new CommunicationFailureException("Unable to connect to the api on '" + baseUrl + "'.");
            }
            callback.accept(bodyTransformer.apply(response.body()));
        }));
    }

    public void callPut(final long id) {
        sendAsyncRequest(requestBuilder.buildPutRequest(RESOURCE_PATH + "/" + id));
    }

    private static void sendAsyncRequest(final HttpRequest request, final BiConsumer<HttpResponse<String>, Throwable> futureConsumer) {
        final var responseFuture = HTTPCLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        responseFuture.whenComplete(futureConsumer);
    }

    private static void sendAsyncRequest(final HttpRequest request) {
        HTTPCLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}
