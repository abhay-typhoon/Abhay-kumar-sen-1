package com.JWT.service.user;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;

import lombok.Setter;


@Getter
@Setter
@Builder
public class User implements UserDetails {
	
	  private String userId;
	    private String name;
	    private String email;
	    private String password; 
	    private String roles; 
	    
	    private User(UserBuilder builder) {
	        this.userId = builder.userId;
	        this.name = builder.name;
	        this.email = builder.email;
	        this.password = builder.password;
	        this.roles = builder.roles;
	    }
	    
	    public User(String userId, String name, String email, String password) {
	        this.userId = userId;
	        this.name = name;
	        this.email = email;
	        this.password = password;
	    }
	    
	    public static UserBuilder builder() {
	        return new UserBuilder();
	    }

	    public static class UserBuilder {
	        private String userId;
	        private String name;
	        private String email;
	        private String password;  // Ensure this field is in the builder
	        private String roles;
	        
	        public UserBuilder userId(String userId) {
	            this.userId = userId;
	            return this;
	        }

	        public UserBuilder name(String name) {
	            this.name = name;
	            return this;
	        }

	        public UserBuilder email(String email) {
	            this.email = email;
	            return this;
	        }

	        public UserBuilder password(String password) {
	            this.password = password;
	            return this;
	        }
	        
	        public UserBuilder roles(String string) {
	            this.roles = string;
	            return this;
	        }

	        public User build() {
	            return new User(this);
	        }

			
	    }	
	
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        // Convert roles to a single string and return as a collection with one element
	        return List.of(new SimpleGrantedAuthority("ROLE_" + String.join(",", roles)));
	    }

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return null;
		}

}
