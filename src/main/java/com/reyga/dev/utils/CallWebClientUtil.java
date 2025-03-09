package com.reyga.dev.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class CallWebClientUtil {

    private final WebClient webClient;

    public CallWebClientUtil(WebClient webClient) {
        this.webClient = webClient;
    }

    public <RES> Mono<RES> get(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType);
    }

    public <RES> Mono<RES> getWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType);
    }

    public <RES> Mono<RES> getWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler);
    }

    public <RES> Mono<RES> getWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError);
    }

    public <RES> Mono<RES> delete(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType);
    }

    public <RES> Mono<RES> deleteWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType);
    }

    public <RES> Mono<RES> deleteWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler);
    }

    public <RES> Mono<RES> deleteWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError);
    }

    /**
     * Using Map.
     */

    public <RES> Mono<RES> getWithMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Function<RES, RES> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType)
                .map(mapFunction);
    }

    public <RES> Mono<RES> getWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler, Function<RES, RES> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .map(mapFunction);
    }

    public <RES> Mono<RES> getWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Function<RES, RES> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .map(mapFunction);
    }

    public <RES> Mono<RES> getWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError, Function<RES, RES> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .map(mapFunction);
    }

    public <RES> Mono<RES> deleteWithMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Function<RES, RES> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType)
                .map(mapFunction);
    }

    public <RES> Mono<RES> deleteWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler, Function<RES, RES> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .map(mapFunction);
    }

    public <RES> Mono<RES> deleteWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Function<RES, RES> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .map(mapFunction);
    }

    public <RES> Mono<RES> deleteWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError, Function<RES, RES> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .map(mapFunction);
    }

    /**
     * Using FlatMap.
     */

    public <RES> Mono<RES> getWithFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Function<RES, Mono<RES>> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType)
                .flatMap(mapFunction);
    }

    public <RES> Mono<RES> getWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler, Function<RES, Mono<RES>> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .flatMap(mapFunction);
    }

    public <RES> Mono<RES> getWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Function<RES, Mono<RES>> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .flatMap(mapFunction);
    }

    public <RES> Mono<RES> getWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError, Function<RES, Mono<RES>> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .flatMap(mapFunction);
    }

    public <RES> Mono<RES> deleteWithFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Function<RES, Mono<RES>> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType)
                .flatMap(mapFunction);
    }

    public <RES> Mono<RES> deleteWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler, Function<RES, Mono<RES>> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .flatMap(mapFunction);
    }

    public <RES> Mono<RES> deleteWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Function<RES, Mono<RES>> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .flatMap(mapFunction);
    }

    public <RES> Mono<RES> deleteWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError, Function<RES, Mono<RES>> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .flatMap(mapFunction);
    }

}
