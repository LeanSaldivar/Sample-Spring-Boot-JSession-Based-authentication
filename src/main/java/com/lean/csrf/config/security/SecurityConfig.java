package com.lean.csrf.config.security;

import com.lean.csrf.config.security.utils.CustomUserDetailsServiceImpl;
import com.lean.csrf.config.security.utils.PasswordConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final PasswordConfig passwordConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .csrf(csrf -> csrf.disable())

        .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v2/users/auth/action/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                 .logout(logout -> logout.disable())
                .httpBasic(basic -> basic.disable())
                .formLogin(login -> login.disable())
                .authenticationProvider(apiAuthenticationProvider());

        return http.build();
    }

      @Bean
    public AuthenticationProvider apiAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordConfig.passwordEncoder());
        return authProvider;
    }


}
