package com.fortest.jwtsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final JwtEncoder jwtEncoder;

    @GetMapping("/user-token")
    @Secured("ROLE_USER")
    public Token getUserToken(JwtAuthenticationToken jwtToken) {
        return new Token(jwtToken.getToken(), jwtToken.getAuthorities());
    }

    @GetMapping("/admin-token")
    @Secured("ROLE_ADMIN")
    public Token getAdminToken(JwtAuthenticationToken jwtToken) {
        return new Token(jwtToken.getToken(), jwtToken.getAuthorities());
    }

    @GetMapping("/introspect")
    public Token introspect(JwtAuthenticationToken jwtToken) {
        return new Token(jwtToken.getToken(), jwtToken.getAuthorities());
    }

    @GetMapping("/can-not-access")
    @Secured("very-difficult-role")
    public Token canNotAccess(JwtAuthenticationToken jwtToken) {
        return new Token(jwtToken.getToken(), jwtToken.getAuthorities());
    }

    @GetMapping("/get-token")
    public Jwt getEncodedToken(@RequestParam String user) {
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(3600))
            .subject(user)
            .claim("roles", user.toUpperCase())
            .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(header, claims);
        return jwtEncoder.encode(jwtEncoderParameters);
    }

    @GetMapping("/get-tokens")
    public Jwt getEncodedToken(
        @RequestParam String user,
        @RequestParam String role1,
        @RequestParam String role2) {
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(3600))
            .subject(user)
            .claim("roles", List.of(role1.toUpperCase(), role2.toUpperCase()))
            .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(header, claims);
        return jwtEncoder.encode(jwtEncoderParameters);
    }

    }
