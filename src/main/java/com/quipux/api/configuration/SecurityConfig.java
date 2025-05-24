package com.quipux.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactivamos CSRF para pruebas con Postman
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/lists/**").authenticated() // Se requiere autenticación para la API
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults()); // Usamos autenticación básica

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("admin")
                .password("{noop}admin123") // {noop} indica sin codificación para pruebas
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}

