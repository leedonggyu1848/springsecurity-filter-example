package com.fortest.jwtsecurity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DgJwtAuthenticationConverter implements Converter<Jwt, JwtAuthenticationToken> {
    private static final String ROLES = "roles";
    private static final String SCOPES = "scopes";

    @Override
    public JwtAuthenticationToken convert(Jwt source) {
        return new JwtAuthenticationToken(source, extractAuthorities(source), source.getSubject());
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (jwt.hasClaim(ROLES))
            authorities.addAll(jwt.getClaimAsStringList(ROLES).stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
        if (jwt.hasClaim(SCOPES))
            authorities.addAll(jwt.getClaimAsStringList(SCOPES).stream()
                .map(scope -> "SCOPE_" + scope)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
        return authorities;
    }
}
