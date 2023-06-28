package com.fortest.jwtsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FilterExceptionManager {
    private static final String ATTRIBUTE_NAME = "dg-exception";
    private static final String STATUS_CODE_NAME = "dg-status-code";
    private static final String CONTENT_TYPE_NAME = "Content-Type";
    private static final String CONTENT_TYPE = "application/json";
    private static final String UTF_8 = "utf-8";

    public void setException(HttpServletRequest request, String message, int statusCode) {
        if (request.getAttribute(ATTRIBUTE_NAME) != null)
            return;
        request.setAttribute(ATTRIBUTE_NAME, message);
        request.setAttribute(STATUS_CODE_NAME, statusCode);
    }

    public void handleException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getAttribute(ATTRIBUTE_NAME) != null) {
            String message = (String) request.getAttribute(ATTRIBUTE_NAME);
            response.setStatus((int) request.getAttribute(STATUS_CODE_NAME));
            response.setHeader(CONTENT_TYPE_NAME, CONTENT_TYPE);
            response.setCharacterEncoding(UTF_8);
            response.getWriter().write(message);
        }
    }


}
