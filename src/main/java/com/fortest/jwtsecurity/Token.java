package com.fortest.jwtsecurity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;

@Getter
public class Token {
    private final Jwt token;
    private final Collection<GrantedAuthority> authorities;

    public Token(Jwt token, Collection<GrantedAuthority> authorities) {
        this.token = token;
        this.authorities = authorities;
    }
}
