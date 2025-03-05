package com.reyga.dev.utils;

import com.reyga.dev.dto.responses.ErrorDetailResponseDto;
import com.reyga.dev.dto.responses.ErrorResponseDto;
import com.reyga.dev.dto.responses.ValidationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResponseEntityUtil {
    public ResponseEntityUtil() {
    }

    public static <T> ResponseEntity<T> createResponse(T data, HttpStatus status) {
        return new ResponseEntity<>(data, status);
    }

    public static ResponseEntity<ErrorResponseDto> createErrorResponse(HttpStatus status, String code, String message) {
       ErrorResponseDto error = new ErrorResponseDto();
       error.setStatus("FAILED");
       error.setCode(code);
       error.setMessage(message);
       return new ResponseEntity<>(error, status);
    }

    public static ResponseEntity<ErrorResponseDto> createErrorResponseWithDetail(HttpStatus status, String code, String message, String business, String additionalInfo) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setStatus("FAILED");
        error.setCode(code);
        error.setMessage(message);
        error.setDetails(ErrorDetailResponseDto.Builder.newBuilder()
                        .business(business)
                        .additionalInfo(additionalInfo)
                        .timestamp(DateUtil.getTimestamp(LocalDateTime.now()))
                        .build());
        return new ResponseEntity<>(error, status);
    }

    public static ResponseEntity<ValidationResponseDto> createValidationResponse(HttpStatus status, String code, String message, Set<String> errors, List<Map<String, String>> errorsList, Map<String, String> errorMap) {
        ValidationResponseDto error = new ValidationResponseDto();
        error.setStatus("FAILED");
        error.setCode(code);
        error.setMessage(message);
        error.setErrors(errors);
        error.setErrorsList(errorsList);
        error.setErrorDetail(errorMap);
        return new ResponseEntity<>(error, status);
    }
}
