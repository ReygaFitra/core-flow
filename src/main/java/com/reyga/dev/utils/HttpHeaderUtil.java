package com.reyga.dev.utils;

import com.reyga.dev.enumeration.HEADER;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpHeaderUtil {

    public static String getRequestId() {
        return RequestContextHolder.getRequestAttributes() != null ? ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader(HEADER.REQUEST_ID.getValue()) : null;
    }

    public static String getClientDate(HttpServletRequest request) {
        return request != null ? request.getHeader("X-Date") : null;
    }

    public static String getLatitude(HttpServletRequest request) {
        return request != null ? request.getHeader("X-Latitude") : null;
    }

    public static String getLongitude(HttpServletRequest request) {
        return request != null ? request.getHeader("X-Longitude") : null;
    }

    public static String getClientInfo(HttpServletRequest request) {
        return request != null ? request.getHeader(HEADER.USER_AGENT.getValue()) : null;
    }
}
