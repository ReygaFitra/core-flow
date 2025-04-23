package com.reyga.dev.logging;

import com.reyga.dev.dto.requests.RequestLoggingDto;
import com.reyga.dev.enumeration.HEADER;
import com.reyga.dev.utils.CommonLogger;
import com.reyga.dev.utils.LoggingUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.UUID;

@Aspect
@Component
public class AspectComponent {

    private final CommonLogger logger;

    public AspectComponent(CommonLogger logger) {
        this.logger = logger;
    }

    @Pointcut(value = "@annotation(com.reyga.dev.annotations.AspectLogExecution)")
    private void pointCut(){
    };

    @Around(value = "pointCut()")
    public Object processRequestIntercept(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (requestAttributes != null) {
            request = ((ServletRequestAttributes) requestAttributes).getRequest();
        }

        MDC.put(HEADER.REQUEST_ID.getValue(), UUID.randomUUID().toString());
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        RequestLoggingDto requestDto = new RequestLoggingDto();
        LoggingUtil.constructRequestBodyAndRequestMultiPart(joinPoint.getArgs(), method, requestDto);
        LoggingUtil.extractRequestParam(request, requestDto);
        LoggingUtil.getUrl(requestDto, request);

        MDC.put(HEADER.REQUEST.getValue(), requestDto.toString());
        try {
            stopWatch.start();
            this.logger.infoServiceStart(joinPoint.getSignature().getName());
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            long executionTime = stopWatch.getTotalTimeMillis();
            MDC.put(HEADER.RESPONSE_TIME.getValue(), executionTime + " ms");
            MDC.put(HEADER.PACKAGE_INFO.getValue(), joinPoint.getTarget().getClass().getName());
            this.logger.infoServiceEnd(joinPoint.getSignature().getName());
        }
    }
}
