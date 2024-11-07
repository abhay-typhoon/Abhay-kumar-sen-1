package com.JWT.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.JWT.service.user.User;

@Configuration
public class AppConfig {
	
	public UserDetailsService userDetailsService() {
		
		UserDetails user1 = User.builder().name("harsh").password("abc").roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(user1);
		
	}
		
		
		@Bean
		public PasswordEncoder passwordencoder() {
			
			return new BCryptPasswordEncoder();
		}
		

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
		
	}
	
	


