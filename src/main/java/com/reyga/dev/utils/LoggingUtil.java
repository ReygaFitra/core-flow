package com.reyga.dev.utils;

import com.reyga.dev.dto.requests.RequestLoggingDto;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class LoggingUtil {
    public LoggingUtil() {
    }

    public static void constructRequestBodyAndRequestMultiPart(Object[] args, Method method, RequestLoggingDto requestDto) {
        Map<String, Object> requestMultiPart = new HashMap();

        for(int argIndex = 0; argIndex < args.length; ++argIndex) {
            for(Annotation annotation : method.getParameterAnnotations()[argIndex]) {
                if (annotation instanceof RequestBody) {
                    requestDto.setRequestBody(args[argIndex]);
                } else if (annotation instanceof RequestPart) {
                    RequestPart requestParam = (RequestPart)annotation;
                    if (args[argIndex] instanceof MultipartFile) {
                        requestMultiPart.put(requestParam.value(), ((MultipartFile)args[argIndex]).getOriginalFilename());
                    } else {
                        requestMultiPart.put(requestParam.value(), args[argIndex]);
                    }
                }
            }
        }

        if (!CollectionUtils.isEmpty(requestMultiPart)) {
            requestDto.setRequestMultiPart(requestMultiPart);
        }

    }

    public static void extractRequestParam(HttpServletRequest request, RequestLoggingDto requestDto) {
        if (request != null) {
            Iterator<String> paramsItr = request.getParameterNames().asIterator();
            Map<String, Object> map = new HashMap();

            while(paramsItr.hasNext()) {
                String paramName = (String)paramsItr.next();
                map.put(paramName, request.getParameter(paramName));
            }

            if (!CollectionUtils.isEmpty(map)) {
                requestDto.setRequestParams(map);
            }

            map = (Map)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            if (map != null && !map.isEmpty()) {
                requestDto.setRequestPathVariable(map);
            }
        }

    }

    public static String getUrl(RequestLoggingDto requestDto, HttpServletRequest request) {
        String url = HttpUtil.getUriPath(request);
        if (requestDto != null && requestDto.getRequestPathVariable() != null) {
            for(int i = 0; i < requestDto.getRequestPathVariable().size(); ++i) {
                url = url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
                url = url.substring(0, url.lastIndexOf("/"));
            }
        }

        return url;
    }

    public Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            if (MDC.get(key) == null || MDC.get(key).isEmpty()) {
                MDC.put(key, value);
            }
            map.put(key, value);
        }

        return map;
    }
}
