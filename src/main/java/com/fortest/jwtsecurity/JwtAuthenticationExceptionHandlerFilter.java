package com.fortest.jwtsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationExceptionHandlerFilter extends OncePerRequestFilter {
    private final FilterExceptionManager filterExceptionManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            filterExceptionManager.setException(request, "Filter !", HttpServletResponse.SC_PRECONDITION_FAILED);
            filterExceptionManager.handleException(request, response);
        }
    }
}
