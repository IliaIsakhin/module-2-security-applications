package com.epam.edu.module2securityapplications.util;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtils {
    
    private static final String TARGET_REQUEST_HEADER = "X-Forwarded-For";
    
    private HttpRequestUtils() {
        
    }
    
    public static String extractSourceIp(HttpServletRequest request) {
        final String header = request.getHeader(TARGET_REQUEST_HEADER);
        return header == null ? request.getRemoteAddr() : header.split(",")[0];
    }
}
