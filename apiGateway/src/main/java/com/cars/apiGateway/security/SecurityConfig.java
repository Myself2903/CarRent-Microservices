package com.cars.apiGateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception{
        http.authorizeExchange(auth -> auth
                .pathMatchers("/").permitAll()
                .anyExchange().authenticated()
        )
        .oauth2Login(Customizer.withDefaults())
        .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    } 
}
