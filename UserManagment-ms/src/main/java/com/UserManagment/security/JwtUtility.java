package com.UserManagment.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.springframework.stereotype.Component;


@Component
public class JwtUtility {
	
	private String secretKey = "your_secret_key";

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            // Parse the token and check if we get a valid subject
            String subject = Jwts.parser()
                                 .setSigningKey(secretKey)
                                 .parseClaimsJws(token)
                                 .getBody()
                                 .getSubject();
            return subject != null; // Return true if parsing was successful
        } catch (Exception e) {
            // If any exception occurs, the token is invalid
            return false;
        }
    }

    
    // Extract email (subject) from JWT token
    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

}
