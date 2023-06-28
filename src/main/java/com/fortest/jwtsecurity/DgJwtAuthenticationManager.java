package com.fortest.jwtsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DgJwtAuthenticationManager implements AuthenticationManager {
    private final DgJwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (jwtAuthenticationProvider.supports(authentication.getClass()))
            return jwtAuthenticationProvider.authenticate(authentication);
        throw new AuthenticationServiceException("Unsupported authentication type: " + authentication.getClass().getName());
    }
}
