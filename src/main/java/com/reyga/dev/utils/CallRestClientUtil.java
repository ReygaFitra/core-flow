package com.reyga.dev.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.function.Predicate;

@Component
public class CallRestClientUtil {

    private final RestClient restClient;

    public CallRestClientUtil(RestClient restClient) {
        this.restClient = restClient;
    }

    public <RES> RES get(String uri, MediaType mediaType, Class<RES> responseType) {
        return restClient.get()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .body(responseType);
    }

    public <RES> RES getWithErrorHandling(String uri, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        return restClient.get()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES getWithErrorHandling(String uri, MediaType mediaType, Class<RES> responseType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        return restClient.get()
                .uri(uri)
                .accept(mediaType)
                .exchange(fullResponseHandler);
    }

    public <RES> RES post(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType) {
        return restClient.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .body(responseType);
    }

    public <RES> RES postWithErrorHandling(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType,
                                          Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        return restClient.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES postWithErrorHandling(String uri, MediaType contentType, MediaType acceptMediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        return restClient.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .exchange(fullResponseHandler);
    }

    public <RES> RES put(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType) {
        return restClient.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .body(responseType);
    }

    public <RES> RES putWithErrorHandling(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType,
                                           Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        return restClient.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES putWithErrorHandling(String uri, MediaType contentType, MediaType acceptMediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        return restClient.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .exchange(fullResponseHandler);
    }

    public <RES> RES delete(String uri, MediaType mediaType, Class<RES> responseType) {
        return restClient.delete()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .body(responseType);
    }

    public <RES> RES deleteWithErrorHandling(String uri, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        return restClient.delete()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES deleteWithErrorHandling(String uri, MediaType mediaType, Class<RES> responseType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        return restClient.get()
                .uri(uri)
                .accept(mediaType)
                .exchange(fullResponseHandler);
    }

    /**
     * Using RestClient toBodilessEntity.
     */

    public ResponseEntity<Void> getWithBodilessEntity(String uri, MediaType mediaType) {
        return restClient.get()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> getWithErrorHandlingBodilessEntity(String uri, MediaType mediaType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        return restClient.get()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .toBodilessEntity();
    }

    public ResponseEntity<Void> postWithBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType) {
        return restClient.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> postWithErrorHandlingBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType,
                                           Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        return restClient.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .toBodilessEntity();
    }

    public ResponseEntity<Void> putBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType) {
        return restClient.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> putWithErrorHandlingBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType,
                                          Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        return restClient.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .body(requestType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .toBodilessEntity();
    }

    public ResponseEntity<Void> deleteBodilessEntity(String uri, MediaType mediaType) {
        return restClient.delete()
                .uri(uri)
                .accept(mediaType)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> deleteWithErrorHandlingBodilessEntity(String uri, MediaType mediaType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        return restClient.delete()
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
        return restClient.get()
                .uri(uri)
                .accept(mediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .retrieve()
                .body(responseType);
    }

    public <RES> RES post(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType, Map<String, String> headers) {
        return restClient.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .body(requestType)
                .retrieve()
                .body(responseType);
    }

    public <RES> RES put(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType, Map<String, String> headers) {
        return restClient.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .body(requestType)
                .retrieve()
                .body(responseType);
    }

    public <RES> RES delete(String uri, MediaType mediaType, Class<RES> responseType, Map<String, String> headers) {
        return restClient.delete()
                .uri(uri)
                .accept(mediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .retrieve()
                .body(responseType);
    }

    public ResponseEntity<Void> getWithBodilessEntity(String uri, MediaType mediaType, Map<String, String> headers) {
        return restClient.get()
                .uri(uri)
                .accept(mediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> postWithBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Map<String, String> headers) {
        return restClient.post()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .body(requestType)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> putWithBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Map<String, String> headers) {
        return restClient.put()
                .uri(uri)
                .contentType(contentType)
                .accept(acceptMediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .body(requestType)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> deleteWithBodilessEntity(String uri, MediaType mediaType, Map<String, String> headers) {
        return restClient.delete()
                .uri(uri)
                .accept(mediaType)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .retrieve()
                .toBodilessEntity();
    }

    public <RES> RES getWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        return restClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES getWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        return restClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchange(fullResponseHandler);
    }
    public <RES> RES postWithErrorHandling(String uri, Map<String, String> headers, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType,
                                           Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        return restClient.post()
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
        return restClient.post()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .contentType(contentType)
                .accept(acceptMediaType)
                .exchange(fullResponseHandler);
    }
    public <RES> RES putWithErrorHandling(String uri, Map<String, String> headers, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType,
                                          Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        return restClient.put()
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
        return restClient.put()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .contentType(contentType)
                .accept(acceptMediaType)
                .exchange(fullResponseHandler);
    }
    public <RES> RES deleteWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler) {
        return restClient.delete()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .retrieve()
                .onStatus(statusError, errorHandler)
                .body(responseType);
    }

    public <RES> RES deleteWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler) {
        return restClient.get()
                .uri(uri)
                .headers(httpHeaders -> headers.forEach(httpHeaders::set))
                .accept(mediaType)
                .exchange(fullResponseHandler);
    }

}
