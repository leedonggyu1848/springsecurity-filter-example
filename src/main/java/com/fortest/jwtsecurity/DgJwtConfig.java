package com.fortest.jwtsecurity;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
public class DgJwtConfig {
    private final static String secretKey = "c9c3abb6e3fb5e1ee73682139284e20a4b59f0ca0f19fd420ac9688d16301646";
    private final static MacAlgorithm algorithm = MacAlgorithm.HS256;
    private final static SecretKey key = new SecretKeySpec(secretKey.getBytes(), algorithm.getName());

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
            .withSecretKey(key)
            .macAlgorithm(algorithm)
            .build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(key));
    }
}
