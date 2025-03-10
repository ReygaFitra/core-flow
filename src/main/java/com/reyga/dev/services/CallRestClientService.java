package com.reyga.dev.services;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.function.Predicate;

public interface CallRestClientService {
    <RES> RES get(String uri, MediaType mediaType, Class<RES> responseType);
    <RES> RES getWithErrorHandling(String uri, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler);
    <RES> RES getWithErrorHandling(String uri, MediaType mediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler);
    <RES> RES post(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType);
    <RES> RES postWithErrorHandling(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType,
                                    Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler);
    <RES> RES postWithErrorHandling(String uri, MediaType contentType, MediaType acceptMediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler);
    <RES> RES put(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType);
    <RES> RES putWithErrorHandling(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType,
                                   Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler);
    <RES> RES putWithErrorHandling(String uri, MediaType contentType, MediaType acceptMediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler);
    <RES> RES delete(String uri, MediaType mediaType, Class<RES> responseType);
    <RES> RES deleteWithErrorHandling(String uri, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler);
    <RES> RES deleteWithErrorHandling(String uri, MediaType mediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler);
    ResponseEntity<Void> getWithBodilessEntity(String uri, MediaType mediaType);
    ResponseEntity<Void> getWithErrorHandlingBodilessEntity(String uri, MediaType mediaType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler);
    ResponseEntity<Void> postWithBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType);
    ResponseEntity<Void> postWithErrorHandlingBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType,
                                                             Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler);
    ResponseEntity<Void> putBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType);
    ResponseEntity<Void> putWithErrorHandlingBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType,
                                                            Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler);
    ResponseEntity<Void> deleteBodilessEntity(String uri, MediaType mediaType);
    ResponseEntity<Void> deleteWithErrorHandlingBodilessEntity(String uri, MediaType mediaType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler);
    <RES> RES get(String uri, MediaType mediaType, Class<RES> responseType, Map<String, String> headers);
    <RES> RES post(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType, Map<String, String> headers);
    <RES> RES put(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType, Map<String, String> headers);
    <RES> RES delete(String uri, MediaType mediaType, Class<RES> responseType, Map<String, String> headers);
    ResponseEntity<Void> getWithBodilessEntity(String uri, MediaType mediaType, Map<String, String> headers);
    ResponseEntity<Void> postWithBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Map<String, String> headers);
    ResponseEntity<Void> putWithBodilessEntity(String uri, MediaType contentType, MediaType acceptMediaType, Object requestType, Map<String, String> headers);
    ResponseEntity<Void> deleteWithBodilessEntity(String uri, MediaType mediaType, Map<String, String> headers);
    <RES> RES getWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler);
    <RES> RES getWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler);
    <RES> RES postWithErrorHandling(String uri, Map<String, String> headers, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType,
                          Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler);
    <RES> RES postWithErrorHandling(String uri, Map<String, String> headers, MediaType contentType, MediaType acceptMediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler);
    <RES> RES putWithErrorHandling(String uri, Map<String, String> headers, MediaType contentType, MediaType acceptMediaType, Object requestType, Class<RES> responseType,
                                   Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler);
    <RES> RES putWithErrorHandling(String uri, Map<String, String> headers, MediaType contentType, MediaType acceptMediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler);
    <RES> RES deleteWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, Class<RES> responseType, Predicate<HttpStatusCode> statusError, RestClient.ResponseSpec.ErrorHandler errorHandler);
    <RES> RES deleteWithErrorHandling(String uri, Map<String, String> headers, MediaType mediaType, RestClient.RequestHeadersSpec.ExchangeFunction<RES> fullResponseHandler);
}
