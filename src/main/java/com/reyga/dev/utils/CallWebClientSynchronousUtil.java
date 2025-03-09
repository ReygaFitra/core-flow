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
public class CallWebClientSynchronousUtil {

    private final WebClient webClient;

    public CallWebClientSynchronousUtil(WebClient webClient) {
        this.webClient = webClient;
    }

    public <RES> RES get(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    public <RES> RES getWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .block();
    }

    public <RES> RES getWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .block();
    }

    public <RES> RES getWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .block();
    }

    public <RES> RES delete(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    public <RES> RES deleteWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .block();
    }

    public <RES> RES deleteWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .block();
    }

    public <RES> RES deleteWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .block();
    }

    public <RES> RES post(String uri, Map<String, String> headers, MediaType mediaType, Object request, Class<RES> responseType) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    public <RES> RES postWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Object request, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .block();
    }

    public <RES> RES postWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Object request, Function<ClientResponse, ? extends Mono<RES>> responseHandler) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .exchangeToMono(responseHandler)
                .block();
    }

    public <RES> RES postWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Object request, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .block();
    }

    public <RES> RES put(String uri, Map<String, String> headers, MediaType mediaType, Object request, Class<RES> responseType) {
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    public <RES> RES putWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Object request, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler) {
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .block();
    }

    public <RES> RES putWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Object request, Function<ClientResponse, ? extends Mono<RES>> responseHandler) {
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .exchangeToMono(responseHandler)
                .block();
    }

    public <RES> RES putWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Object request, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError) {
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .block();
    }

    /**
     * Using Map.
     */

    public <RES> RES getWithMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Function<RES, RES> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType)
                .map(mapFunction)
                .block();
    }

    public <RES> RES getWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler, Function<RES, RES> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .map(mapFunction)
                .block();
    }

    public <RES> RES getWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Function<RES, RES> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .map(mapFunction)
                .block();
    }

    public <RES> RES getWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError, Function<RES, RES> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .map(mapFunction)
                .block();
    }

    public <RES> RES deleteWithMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Function<RES, RES> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType)
                .map(mapFunction)
                .block();
    }

    public <RES> RES deleteWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler, Function<RES, RES> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .map(mapFunction)
                .block();
    }

    public <RES> RES deleteWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Function<RES, RES> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .map(mapFunction)
                .block();
    }

    public <RES> RES deleteWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError, Function<RES, RES> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .map(mapFunction)
                .block();
    }

    public <RES> RES postWithMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Function<RES, RES> mapFunction) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType)
                .map(mapFunction)
                .block();
    }

    public <RES> RES postWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler, Function<RES, RES> mapFunction) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .map(mapFunction)
                .block();
    }

    public <RES> RES postWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Function<RES, RES> mapFunction) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .exchangeToMono(responseHandler)
                .map(mapFunction)
                .block();
    }

    public <RES> RES postWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError, Function<RES, RES> mapFunction) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .map(mapFunction)
                .block();
    }

    public <RES> RES putWithMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Function<RES, RES> mapFunction) {
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType)
                .map(mapFunction)
                .block();
    }

    public <RES> RES putWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler, Function<RES, RES> mapFunction) {
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .map(mapFunction)
                .block();
    }

    public <RES> RES putWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Function<RES, RES> mapFunction) {
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .exchangeToMono(responseHandler)
                .map(mapFunction)
                .block();
    }

    public <RES> RES putWithErrorHandlingAndMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError, Function<RES, RES> mapFunction) {
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .map(mapFunction)
                .block();
    }

    /**
     * Using FlatMap.
     */

    public <RES> RES getWithFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Function<RES, Mono<RES>> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES getWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler, Function<RES, Mono<RES>> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES getWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Function<RES, Mono<RES>> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES getWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError, Function<RES, Mono<RES>> mapFunction) {
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES deleteWithFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Function<RES, Mono<RES>> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .bodyToMono(responseType)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES deleteWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler, Function<RES, Mono<RES>> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES deleteWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Function<RES, Mono<RES>> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES deleteWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError, Function<RES, Mono<RES>> mapFunction) {
        return webClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES postWithFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Class<RES> responseType, Function<RES, Mono<RES>> mapFunction) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(responseType)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES postWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler, Function<RES, Mono<RES>> mapFunction) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES postWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Function<RES, Mono<RES>> mapFunction) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .exchangeToMono(responseHandler)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES postWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError, Function<RES, Mono<RES>> mapFunction) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES putWithFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Class<RES> responseType, Function<RES, Mono<RES>> mapFunction) {
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(responseType)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES putWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Class<RES> responseType, Predicate<HttpStatusCode> statusError, Function<ClientResponse, Mono<? extends Throwable>> errorHandler, Function<RES, Mono<RES>> mapFunction) {
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .bodyToMono(responseType)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES putWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Function<RES, Mono<RES>> mapFunction) {
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .exchangeToMono(responseHandler)
                .flatMap(mapFunction)
                .block();
    }

    public <RES> RES putWithErrorHandlingAndFlatMap(String uri, Map<String, String> headers, MediaType mediaType, Object request, Function<ClientResponse, ? extends Mono<RES>> responseHandler, Consumer<? super RES> onSuccess, Consumer<? super Throwable> onError, Function<RES, Mono<RES>> mapFunction) {
        return webClient.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .bodyValue(request)
                .exchangeToMono(responseHandler)
                .doOnSuccess(onSuccess)
                .doOnError(onError)
                .flatMap(mapFunction)
                .block();
    }

}
