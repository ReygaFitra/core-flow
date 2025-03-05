package com.reyga.dev.dto.responses;

public class CommonResponseDto<T> extends BaseResponseDto {
    private T data;

    public CommonResponseDto() {
    }

    public CommonResponseDto(String status, String code, String message) {
        super(status, code, message);
    }

    public CommonResponseDto(T data) {
        this.data = data;
    }

    public CommonResponseDto(String status, String code, String message, T data) {
        super(status, code, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
