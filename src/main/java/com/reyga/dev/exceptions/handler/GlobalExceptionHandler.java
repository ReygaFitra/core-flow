package com.reyga.dev.exceptions.handler;

import com.reyga.dev.dto.responses.ErrorResponseDto;
import com.reyga.dev.dto.responses.ValidationResponseDto;
import com.reyga.dev.enumeration.HEADER;
import com.reyga.dev.exceptions.AppFaultException;
import com.reyga.dev.exceptions.CustomValidationException;
import com.reyga.dev.utils.CommonLogger;
import com.reyga.dev.utils.ResponseEntityUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final CommonLogger logger;

    public GlobalExceptionHandler(CommonLogger logger) {
        this.logger = logger;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponseDto> handleGlobalErrorException(Exception e, HttpServletRequest request) {
        request.setAttribute(HEADER.EXCEPTION.getValue(), e);
        String code;
        String exceptionType;
        HttpStatus httpStatus;
        ObjectMapper mapper = new ObjectMapper();
        final Map<String, Object> errors = new HashMap<>();
        if (e instanceof IllegalArgumentException) {
            code = "91";
            exceptionType = "IllegalArgumentException";
            httpStatus = HttpStatus.BAD_REQUEST;
            errors.put("illegalArgumentException", e.getStackTrace()[0].toString());
            try {
                this.logger.warn("ILLEGAL ARGUMENT EXCEPTION ERROR :", mapper.writeValueAsString(errors));
            } catch (JsonProcessingException e1) {
                this.logger.error("write_log_error", e1.getMessage());
            }
        } else {
            code = "99";
            exceptionType = "Global Error";
            httpStatus = INTERNAL_SERVER_ERROR;
            errors.put("Global Error", e.getStackTrace()[0].toString());
            try {
                this.logger.warn("GLOBAL ERROR :", mapper.writeValueAsString(errors));
            } catch (JsonProcessingException e1) {
                this.logger.error("write_log_error", e1.getMessage());
            }
        }
        this.logger.exception(exceptionType.toUpperCase(), null,e);
        return ResponseEntityUtil.createErrorResponse(httpStatus, code, "GENERAL ERROR");
    }

    @ExceptionHandler({JpaSystemException.class, JDBCException.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDto> handleDatabaseErrorException(Exception e, HttpServletRequest request) {
        request.setAttribute(HEADER.EXCEPTION.getValue(), e);
        String exceptionType = "";
        ObjectMapper mapper = new ObjectMapper();
        final Map<String, Object> errors = new HashMap<>();
        if (e instanceof JpaSystemException) {
            exceptionType = "JpaSystemException";
            errors.put("JPA-SYSTEM-ERROR", e.getStackTrace()[0].toString());
            try {
                this.logger.warn("JPA ERROR :", mapper.writeValueAsString(errors));
            } catch (JsonProcessingException e1) {
                this.logger.error("write_log_error", e1.getMessage());
            }
        }
        if (e instanceof JDBCException) {
            exceptionType = "JDBCException";
            errors.put("JDBC-ERROR", e.getStackTrace()[0].toString());
            try {
                this.logger.warn("JDBC ERROR :", mapper.writeValueAsString(errors));
            } catch (JsonProcessingException e1) {
                this.logger.error("write_log_error", e1.getMessage());
            }
        }
        this.logger.exception(exceptionType.toUpperCase(), null,e);
        return ResponseEntityUtil.createErrorResponse(INTERNAL_SERVER_ERROR, "99", "DATABASE ERROR");
    }

    @ExceptionHandler(AppFaultException.class)
    public ResponseEntity<ErrorResponseDto> handleAppFaultException(AppFaultException appFaultException, HttpServletRequest request) {
        request.setAttribute(HEADER.EXCEPTION.getValue(), appFaultException);
        this.logger.exception("AppFaultException".toUpperCase(), appFaultException.getFaultInfo(),appFaultException);
        return ResponseEntityUtil.createErrorResponse(appFaultException.getStatusCode(), appFaultException.getErrorCode(), appFaultException.getErrorMessage());
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<ValidationResponseDto> handleException(CustomValidationException customValidationException, HttpServletRequest request) {
        request.setAttribute(HEADER.EXCEPTION.getValue(), customValidationException);
        this.logger.exception("CustomValidationException".toUpperCase(), null, customValidationException);
        return ResponseEntityUtil.createValidationResponse(
                HttpStatus.BAD_REQUEST, "04", "Validation Error", customValidationException.getErrorSet(),
                customValidationException.getErrorMapList(), customValidationException.getErrorHashMap()
        );
    }
}
