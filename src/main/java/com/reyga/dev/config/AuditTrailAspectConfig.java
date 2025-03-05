package com.reyga.dev.config;

import com.reyga.dev.annotations.AuditTrail;
import com.reyga.dev.dto.requests.AuditTrailHttpRequestDto;
import com.reyga.dev.dto.requests.RequestLoggingDto;
import com.reyga.dev.dto.responses.BaseResponseDto;
import com.reyga.dev.entities.AuditTrailEntity;
import com.reyga.dev.enumeration.AuditTrailActivityType;
import com.reyga.dev.repositories.AuditTrailRepository;
import com.reyga.dev.utils.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class AuditTrailAspectConfig {

    private final CommonLogger logger;
    private final AuditTrailRepository auditTrailRepository;
    private final ObjectMapper objectMapper;

    public AuditTrailAspectConfig(CommonLogger logger, AuditTrailRepository auditTrailRepository, ObjectMapper objectMapper) {
        this.logger = logger;
        this.auditTrailRepository = auditTrailRepository;
        this.objectMapper = objectMapper;
    }

    @Pointcut(value = "@annotation(com.reyga.dev.annotations.AuditTrail)")
    private void pointCut(){
    };

    @Around(value = "pointCut()")
    @SuppressWarnings("unchecked")
    public Object processRequestIntercept(ProceedingJoinPoint joinPoint) throws Throwable {
        ResponseEntity<? extends BaseResponseDto> result = null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (requestAttributes != null) {
            request = ((ServletRequestAttributes) requestAttributes).getRequest();
        }

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        RequestLoggingDto requestDto = new RequestLoggingDto();
        LoggingUtil.constructRequestBodyAndRequestMultiPart(joinPoint.getArgs(), method, requestDto);
        LoggingUtil.extractRequestParam(request, requestDto);
        LoggingUtil.getUrl(requestDto, request);

        AuditTrail auditTrailAnnotation = method.getAnnotation(AuditTrail.class);
        Object additionalData = getAdditionalData(null, auditTrailAnnotation, requestDto, null, true);
        try {
            result = ((ResponseEntity<? extends BaseResponseDto>) joinPoint.proceed());
            additionalData = getAdditionalData(additionalData, auditTrailAnnotation, requestDto, result, false);
        } catch (Exception e) {
          result = ResponseEntityUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "99", "General error");
        } finally {
            try {
                AuditTrailHttpRequestDto httpRequestDto = new AuditTrailHttpRequestDto();
                httpRequestDto.setUrl(LoggingUtil.getUrl(requestDto, request));
                httpRequestDto.setClientDate(HttpHeaderUtil.getClientDate(request));
                httpRequestDto.setIpAddress(HttpUtil.getClientAddress(request));
                httpRequestDto.setLatitude(HttpHeaderUtil.getLatitude(request));
                httpRequestDto.setLongitude(HttpHeaderUtil.getLongitude(request));
                httpRequestDto.setClientInfo(HttpHeaderUtil.getClientInfo(request));
                httpRequestDto.setHttpMethod(HttpUtil.getHttpMethod(method));
                saveAuditTrail(httpRequestDto, result, requestDto, DateUtil.nowSystemTimezone());
            } catch (JsonProcessingException e) {
                this.logger.error("Error save audit trail", e.getMessage());
            }

        }
        return result;
    }

    private void saveAuditTrail(AuditTrailHttpRequestDto request, ResponseEntity<? extends BaseResponseDto> result, RequestLoggingDto requestLoggingDto, LocalDateTime createdDate) throws JsonProcessingException {
        AuditTrailEntity entity = new AuditTrailEntity();
        entity.setClientDate(request.getClientDate());
        entity.setClientInfo(request.getClientInfo());
        entity.setIpAddress(request.getIpAddress());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
//        entity.setUserId(SecurityUtils.getUserDetailsIfAvailable().isPresent() ? SecurityUtils.getUserId() : null);
        entity.setUserId(null);
        entity.setCreatedDate(createdDate);

        BaseResponseDto responseDto = null;
        if (result != null) {
            responseDto = result.getBody();

            entity.setSuccess(HttpStatus.OK.value() == result.getStatusCode().value());
            entity.setStatusCode(String.valueOf(result.getStatusCode().value()));
        }

//        entity.setActivityType(activityType);
        entity.setActivityType(null);

//        setActivityDescriptionAndMenu(request, entity);

        entity.setRequest(this.objectMapper.writeValueAsString(requestLoggingDto));

        if (responseDto != null) {
            entity.setResponse(this.objectMapper.writeValueAsString(responseDto));
        }

        this.auditTrailRepository.save(entity);
    }

    private Object getAdditionalData(Object additionalData, AuditTrail auditTrailAnnotation, RequestLoggingDto requestDto, ResponseEntity<? extends BaseResponseDto> result,
                                     boolean isBefore) {
        AuditTrailActivityType activityTypeEnum = AuditTrailActivityType.valueOf(auditTrailAnnotation.activityType());

        if (activityTypeEnum.getAuditType().isNeedAdditionalDataBeforeProcess() && !auditTrailAnnotation.skipGetAdditionalData() && isBefore)
//            additionalData = getAdditionalDataService.getBeforeProcess(requestDto, activityTypeEnum);
            additionalData = "additional data1";
        else if (activityTypeEnum.getAuditType().isNeedAdditionalDataAfterProcess() && !auditTrailAnnotation.skipGetAdditionalData() && !isBefore)
//            additionalData = getAdditionalDataService.getAfterProcess(requestDto, activityTypeEnum, result);
            additionalData = "additional data2";

        return additionalData;
    }
}
