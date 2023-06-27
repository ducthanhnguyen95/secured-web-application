package com.example.secured.web.application.config;

import com.example.secured.web.application.handlers.CustomAuthenticationFailureHandler;
import com.example.secured.web.application.handlers.CustomAuthenticationSuccessHandler;
import com.example.secured.web.application.services.AuthenticationProviderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    private final AuthenticationProviderService authenticationProviderService;

    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    private final CustomAuthenticationFailureHandler authenticationFailureHandler;

    public ProjectConfig(AuthenticationProviderService authenticationProviderService, CustomAuthenticationSuccessHandler authenticationSuccessHandler, CustomAuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationProviderService = authenticationProviderService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProviderService);
        http.formLogin(c -> c.successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler));
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        return http.build();
    }
}
