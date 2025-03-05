package com.reyga.dev.dto.responses;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseResponseDto {
    protected String status;
    protected String code;
    protected String message;

    public BaseResponseDto() {
    }

    public BaseResponseDto(String status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
