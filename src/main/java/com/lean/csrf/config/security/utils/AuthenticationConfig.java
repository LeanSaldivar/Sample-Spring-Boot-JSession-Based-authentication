package com.lean.csrf.config.security.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {
    private final AuthenticationConfiguration authenticationConfig;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationConfig.getAuthenticationManager();
    }

}
