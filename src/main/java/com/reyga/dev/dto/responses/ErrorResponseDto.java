package com.reyga.dev.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDto extends BaseResponseDto {
    private ErrorDetailResponseDto details;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(String status, String code, String message) {
        super(status, code, message);
    }

    public ErrorResponseDto(ErrorDetailResponseDto details) {
        this.details = details;
    }

    public ErrorResponseDto(String status, String code, String message, ErrorDetailResponseDto details) {
        super(status, code, message);
        this.details = details;
    }

    private ErrorResponseDto(Builder builder) {
        setStatus(builder.status);
        setCode(builder.code);
        setMessage(builder.message);
        setDetails(builder.details);
    }

    public ErrorDetailResponseDto getDetails() {
        return details;
    }

    public void setDetails(ErrorDetailResponseDto details) {
        this.details = details;
    }

    public static final class Builder {
        private String status;
        private String code;
        private String message;
        private ErrorDetailResponseDto details;

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

        public Builder details(ErrorDetailResponseDto val) {
            details = val;
            return this;
        }

        public ErrorResponseDto build() {
            return new ErrorResponseDto(this);
        }
    }
}
