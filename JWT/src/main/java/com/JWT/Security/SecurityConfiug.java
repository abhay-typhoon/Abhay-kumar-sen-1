package com.JWT.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.JWT.Token.JwtTokenProvider;

@EnableWebSecurity
public class SecurityConfiug  extends WebSecurityConfiguration {

	  @Autowired
	    private JwtTokenProvider jwtTokenProvider;

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	            .authorizeRequests()
	            .antMatchers("/api/auth/**").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .apply(new JwtConfigurer(jwtTokenProvider));
	    }

	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    @Override
	    public UserDetailsService userDetailsService() {
	        return new UserDetailsServiceImpl();
	    }
	
}
