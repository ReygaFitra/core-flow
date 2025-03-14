package com.reyga.dev.controllers;

import com.reyga.dev.dto.responses.CommonResponseDto;

public abstract class BaseController<R> {

    protected CommonResponseDto<R> constructSuccessResponse(R data, String code, String message) {
        CommonResponseDto<R> commonResponseDto = new CommonResponseDto<>();
        commonResponseDto.setStatus("Success");
        commonResponseDto.setCode(code);
        commonResponseDto.setMessage(message);
        commonResponseDto.setData(data);
        return commonResponseDto;
    }

}
