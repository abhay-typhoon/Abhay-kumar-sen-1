package com.JWT.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfiguration {

    @Autowired
    private JwtAuthenticationEntryPOint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Disable CSRF and CORS for testing purposes. Configure as needed for production.
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable()) // Enable and configure CORS if needed
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/home/**").authenticated() // Secure /home/** endpoints
                .requestMatchers("/auth/login").permitAll() // Allow unauthenticated access to /auth/login
                .anyRequest().authenticated() // Secure all other endpoints
            )
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // Handle unauthorized requests
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session management
            );

        // Add JWT filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build(); // Return the configured SecurityFilterChain
    }
}
