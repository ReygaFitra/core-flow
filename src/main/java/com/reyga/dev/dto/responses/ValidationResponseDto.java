package com.reyga.dev.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationResponseDto extends BaseResponseDto {
    private List<Map<String, String>> errorsList;
    private Set<String> errors;
    private Map<String, String> errorDetail;

    public ValidationResponseDto() {
    }

    public ValidationResponseDto(String status, String code, String message) {
        super(status, code, message);
    }

    public ValidationResponseDto(List<Map<String, String>> errorsList, Set<String> errors, Map<String, String> errorDetail) {
        this.errorsList = errorsList;
        this.errors = errors;
        this.errorDetail = errorDetail;
    }

    public ValidationResponseDto(String status, String code, String message, List<Map<String, String>> errorsList, Set<String> errors, Map<String, String> errorDetail) {
        super(status, code, message);
        this.errorsList = errorsList;
        this.errors = errors;
        this.errorDetail = errorDetail;
    }

    private ValidationResponseDto(Builder builder) {
        setStatus(builder.status);
        setCode(builder.code);
        setMessage(builder.message);
        setErrorsList(builder.errorsList);
        setErrors(builder.errors);
        setErrorDetail(builder.errorDetail);
    }

    public List<Map<String, String>> getErrorsList() {
        return errorsList;
    }

    public void setErrorsList(List<Map<String, String>> errorsList) {
        this.errorsList = errorsList;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public void setErrors(Set<String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(Map<String, String> errorDetail) {
        this.errorDetail = errorDetail;
    }

    public static final class Builder {
        private String status;
        private String code;
        private String message;
        private List<Map<String, String>> errorsList;
        private Set<String> errors;
        private Map<String, String> errorDetail;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public Builder code(String val) {
            code = val;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder errorsList(List<Map<String, String>> val) {
            errorsList = val;
            return this;
        }

        public Builder errors(Set<String> val) {
            errors = val;
            return this;
        }

        public Builder errorDetail(Map<String, String> val) {
            errorDetail = val;
            return this;
        }

        public ValidationResponseDto build() {
            return new ValidationResponseDto(this);
        }
    }
}
