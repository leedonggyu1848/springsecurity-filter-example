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

    private final FilterExceptionManager filterExceptionManager;

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            filterExceptionManager.setException(request, "당신의 토큰으로는 접근할 수 없어요!", HttpServletResponse.SC_FORBIDDEN);
            filterExceptionManager.handleException(request, response);
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            filterExceptionManager.setException(request, "토큰을 찾을 수 없어요!", HttpServletResponse.SC_UNAUTHORIZED);
            filterExceptionManager.handleException(request, response);
        };
    }
}
