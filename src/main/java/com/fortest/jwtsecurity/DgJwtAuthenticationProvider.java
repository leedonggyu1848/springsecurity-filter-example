package com.fortest.jwtsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DgJwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtDecoder jwtDecoder;
    private final Converter<Jwt, ? extends JwtAuthenticationToken> jwtAuthenticationConverter;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BearerTokenAuthenticationToken token = (BearerTokenAuthenticationToken) authentication;
        Jwt jwt = getJwt(token);
        return this.jwtAuthenticationConverter.convert(jwt);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(BearerTokenAuthenticationToken.class);
    }

    private Jwt getJwt(BearerTokenAuthenticationToken token) {
        try {
            return this.jwtDecoder.decode(token.getToken());
        } catch (JwtException e) {
            throw new AuthenticationServiceException("jwt관련 오류", e);
        } catch (RuntimeException e) {
            throw new AuthenticationServiceException("jwt 파싱 관련 오류", e);
        }
    }
}
