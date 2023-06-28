package com.fortest.jwtsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    private final DgJwtAuthenticationManager authenticationManager;
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
//            .authorizeHttpRequests(authorize ->
//                authorize
//                    .mvcMatchers("/get-token").permitAll()
//                    .mvcMatchers("/get-tokens").permitAll()
//                    .mvcMatchers("/user-token").hasRole("USER")
//                    .mvcMatchers("/admin-token").hasRole("ADMIN")
//                    .mvcMatchers("/introspect").authenticated()
//                    .anyRequest().authenticated()
//            )
//            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt).build();
            .oauth2ResourceServer()
                .bearerTokenResolver(new DefaultBearerTokenResolver())
                    .jwt()
                    .authenticationManager(authenticationManager)
                .and()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .build();
    }

}
