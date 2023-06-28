package com.fortest.jwtsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletResponse;

@Configuration
@RequiredArgsConstructor
public class ExceptionHandleConfig {
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            handleException(response, accessDeniedException.getMessage() + ": 당신의 토큰으로는 접근할 수 없어 !", HttpServletResponse.SC_FORBIDDEN);
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            handleException(response, authException.getMessage() + ": 당신의 토큰은 잘못되었어 !", HttpServletResponse.SC_UNAUTHORIZED);
        };
    }

    private void handleException(HttpServletResponse response, String message, int statusCode) {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write("{\"message\":\"" + message + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
