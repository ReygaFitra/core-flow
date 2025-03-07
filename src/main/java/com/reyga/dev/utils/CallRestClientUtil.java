package com.reyga.dev.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.function.Predicate;

@Component
public class CallRestClientUtil {

    private final HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory;

    public CallRestClientUtil(HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory) {
        this.httpComponentsClientHttpRequestFactory = httpComponentsClientHttpRequestFactory;
    }

    public <RES> RES get(String uri, MediaType mediaType, Class<RES> responseType) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.get()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .body(responseType);
    }

    public <RES> RES getWithErrorHandling(String uri, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.get()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES getWithErrorHandling(String uri, MediaType mediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.get()
                .uri(uri)
                .accept(mediaType)
                .exchange(fullResponseHandler);
    }

    public <RES> RES post(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .body(responseType);
    }

    public <RES> RES postWithErrorHandling(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType,
                                          Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES postWithErrorHandling(String uri, MediaType contentType, MediaType acceptMediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .exchange(fullResponseHandler);
    }

    public <RES> RES put(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .body(responseType);
    }

    public <RES> RES putWithErrorHandling(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType,
                                           Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES putWithErrorHandling(String uri, MediaType contentType, MediaType acceptMediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .exchange(fullResponseHandler);
    }

    public <RES> RES delete(String uri, MediaType mediaType, Class<RES> responseType) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.delete()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .body(responseType);
    }

    public <RES> RES deleteWithErrorHandling(String uri, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.delete()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES deleteWithErrorHandling(String uri, MediaType mediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.get()
                .uri(uri)
                .accept(mediaType)
                .exchange(fullResponseHandler);
    }

    /**
     * Using RestClient toBodilessEntity.
     */

    public ResponseEntity<Void> getWithBodilessEntity(String uri, MediaType mediaType) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.get()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> getWithErrorHandlingBodilessEntity(String uri, MediaType mediaType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.get()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .toBodilessEntity();
    }

    public ResponseEntity<Void> postWithBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> postWithErrorHandlingBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType,
                                           Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .toBodilessEntity();
    }

    public ResponseEntity<Void> putBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> putWithErrorHandlingBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType,
                                          Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .toBodilessEntity();
    }

    public ResponseEntity<Void> deleteBodilessEntity(String uri, MediaType mediaType) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.delete()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> deleteWithErrorHandlingBodilessEntity(String uri, MediaType mediaType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.delete()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .toBodilessEntity();
    }

    /**
     * Using RestClient Headers.
     */

    public <RES> RES get(String uri, MediaType mediaType, Class<RES> responseType, Map<String, String> headers) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.get()
                .uri(uri)
                .accept(mediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .retrieve()
                .body(responseType);
    }

    public <RES> RES post(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType, Map<String, String> headers) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .body(requestType)
                .retrieve()
                .body(responseType);
    }

    public <RES> RES put(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType, Map<String, String> headers) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .body(requestType)
                .retrieve()
                .body(responseType);
    }

    public <RES> RES delete(String uri, MediaType mediaType, Class<RES> responseType, Map<String, String> headers) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.delete()
                .uri(uri)
                .accept(mediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .retrieve()
                .body(responseType);
    }

    public ResponseEntity<Void> getWithBodilessEntity(String uri, MediaType mediaType, Map<String, String> headers) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.get()
                .uri(uri)
                .accept(mediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> postWithBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Map<String, String> headers) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .body(requestType)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> putWithBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Map<String, String> headers) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .body(requestType)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> deleteWithBodilessEntity(String uri, MediaType mediaType, Map<String, String> headers) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.delete()
                .uri(uri)
                .accept(mediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .retrieve()
                .toBodilessEntity();
    }

    public <RES> RES getWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES getWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchange(fullResponseHandler);
    }
    public <RES> RES postWithErrorHandling(String uri, Map<String, String> headers, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType,
                                           Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES postWithErrorHandling(String uri, Map<String, String> headers, MediaType contentType, MediaType acceptMediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .contentType(contentType)
                .accept(acceptMediaType)
                .exchange(fullResponseHandler);
    }
    public <RES> RES putWithErrorHandling(String uri, Map<String, String> headers, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType,
                                          Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES putWithErrorHandling(String uri, Map<String, String> headers, MediaType contentType, MediaType acceptMediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .contentType(contentType)
                .accept(acceptMediaType)
                .exchange(fullResponseHandler);
    }
    public <RES> RES deleteWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES deleteWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        RestClient client = RestClient
                .builder()
                .requestFactory(httpComponentsClientHttpRequestFactory)
                .build();
        return client.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchange(fullResponseHandler);
    }

}
