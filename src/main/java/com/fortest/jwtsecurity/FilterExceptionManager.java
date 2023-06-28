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
    private static final String UTF_8 = "utf-8";

    public void setException(HttpServletRequest request, String message, int statusCode) {
        if (request.getAttribute(ATTRIBUTE_NAME) != null)
            return;
        request.setAttribute(ATTRIBUTE_NAME, message);
        request.setAttribute(STATUS_CODE_NAME, statusCode);
    }

    public void handleException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getAttribute(ATTRIBUTE_NAME) != null) {

        }
    }


}
