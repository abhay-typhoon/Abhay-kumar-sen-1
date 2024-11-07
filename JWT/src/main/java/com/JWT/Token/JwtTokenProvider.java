package com.JWT.Token;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	  @Value("${jwt.secret}")
	    private String secretKey;

	    @Value("${jwt.expiration}")
	    private long validityInMilliseconds; // e.g., 3600000 for 1h

	    public String createToken(String username, String role) {
	        Claims claims = Jwts.claims().setSubject(username);
	        claims.put("role", role);

	        Date now = new Date(0, 0, 0);
	        Date validity = new Date(now.getTime() + validityInMilliseconds);

	        return Jwts.builder()
	                .setClaims(claims)
	                .setIssuedAt(now)
	                .setExpiration(validity)
	                .signWith(SignatureAlgorithm.HS256, secretKey)
	                .compact();
	    }

	    public String getUsername(String token) {
	        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	    }

	    public boolean validateToken(String token) {
	        try {
	            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
	            return true;
	        } catch (JwtException | IllegalArgumentException e) {
	            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
	        }
	    }
	
}

