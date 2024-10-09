package com.lesson.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
    WebSecurityConfig is a configuration class to define Security Settings
    that will protect our endpoints from unauthorized access:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests(authorize ->
                        authorize
                                .anyRequest().authenticated())
                .x509()
                .authenticationUserDetailsService( new X509AuthenticatedUserDetailsService() );

        return http.build();
    }


}
