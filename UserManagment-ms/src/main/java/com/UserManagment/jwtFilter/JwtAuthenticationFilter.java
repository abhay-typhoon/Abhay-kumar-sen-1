package com.UserManagment.jwtFilter;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.UserManagment.security.JwtUtility;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtility jwtUtility;

    // List of public endpoints
    private final List<String> publicEndpoints = List.of(
            "/api/login", 
            "/api/register", 
            "/api/verify", 
            "/AuthVerification/forgot-password", 
            "/AuthVerification/reset-password"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Debugging logs
        System.out.println("Request Path: " + request.getRequestURI());
        System.out.println("Authorization Header: " + request.getHeader("Authorization"));

        String path = request.getRequestURI();
        System.out.println("Request Path: " + path);

        // Skip JWT validation for public endpoints
        if (shouldNotFilter(request)) {
        	 System.out.println("Skipping JWT validation for public endpoint: " + path);
        	filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");

        // Check if the Authorization header exists and starts with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            // Validate the JWT token
            if (jwtUtility.validateToken(token)) {
                String email = jwtUtility.extractEmail(token);

                // Create authentication object
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(email, null, null);

                // Set authentication in SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } else {
            // If no valid token, respond with 403 for protected endpoints
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Access Denied: Missing or Invalid Token");
            return;
        }

        filterChain.doFilter(request, response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        boolean isPublic = publicEndpoints.stream().anyMatch(path::startsWith);
        System.out.println("Checking if path should be filtered: " + path + " | Is public: " + isPublic);
        return isPublic;
    }

}
