package com.reyga.dev.logging;

import com.reyga.dev.enumeration.HEADER;
import com.reyga.dev.utils.CommonLogger;
import com.reyga.dev.utils.LoggingUtil;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.UUID;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private final LoggingUtil loggingUtil;
    private final CommonLogger logger;

    public LogInterceptor(LoggingUtil loggingUtil, CommonLogger logger) {
        this.loggingUtil = loggingUtil;
        this.logger = logger;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String> headersMap = this.loggingUtil.buildHeadersMap(request);
        headersMap.put(HEADER.REQUEST_ID.getValue(), UUID.randomUUID().toString());
        headersMap.put(HEADER.METHOD.getValue(), request.getMethod());
        headersMap.put(HEADER.REQUEST_ENDPOINT.getValue(), request.getRequestURI());
        headersMap.put(HEADER.FORWARDED_FOR.getValue(), request.getRemoteAddr());
        headersMap.put(HEADER.ACCESS_TOKEN.getValue(), request.getHeader(HEADER.ACCESS_TOKEN.getValue()) == null ? null : request.getHeader(HEADER.ACCESS_TOKEN.getValue()));
        headersMap.put(HEADER.USERNAME.getValue(), request.getHeader(HEADER.USERNAME.getValue()) == null ? null : request.getHeader(HEADER.USERNAME.getValue()));
        headersMap.put(HEADER.REQUEST.getValue(), MDC.get(HEADER.REQUEST.getValue()));
        headersMap.put(HEADER.USER_AGENT.getValue(), request.getHeader(HEADER.USER_AGENT.getValue()));
        this.logger.info(headersMap.toString());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name()) && handler instanceof HandlerMethod) {
            this.logger.infoAspectLog(
                    MDC.get(HEADER.ACCESS_TOKEN.getValue()), MDC.get(HEADER.USERNAME.getValue()), MDC.get(HEADER.REQUEST_ID.getValue()),
                    MDC.get(HEADER.METHOD.getValue()), response.getStatus(), MDC.get(HEADER.REQUEST_ENDPOINT.getValue()),
                    MDC.get(HEADER.FORWARDED_FOR.getValue()), MDC.get(HEADER.PACKAGE_INFO.getValue()), request.getAttribute(HEADER.EXCEPTION.getValue()).toString(),
                    MDC.get(HEADER.REQUEST.getValue()), request.getAttribute(HEADER.RESPONSE.getValue()).toString(), MDC.get(HEADER.USER_AGENT.getValue()), MDC.get(HEADER.RESPONSE_TIME.getValue())
            );
            MDC.clear();
        }
    }
}
