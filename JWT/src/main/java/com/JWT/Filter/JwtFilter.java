package com.JWT.Filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.JWT.Token.JwtTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter  {
	
	 private JwtTokenProvider jwtTokenProvider;

	    public JwtFilter(JwtTokenProvider jwtTokenProvider) {
	        this.jwtTokenProvider = jwtTokenProvider;
	    }

	    @Override
	    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
	        String token = jwtTokenProvider.resolveToken(req);
	        if (token != null && jwtTokenProvider.validateToken(token)) {
	            Authentication auth = jwtTokenProvider.getAuthentication(token);
	            SecurityContextHolder.getContext().setAuthentication(auth);
	        }
	        filterChain.doFilter(req, res);
	    }

	
	
}
