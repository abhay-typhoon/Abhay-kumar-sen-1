package com.JWT.Model;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {
	
	private String JwtToken;
	private String userName;
	private String token;
	private String type;
	private String username;
	private List<String> roles;
	public String getJwtToken() {
		return JwtToken;
	}
	public void setJwtToken(String jwtToken) {
		JwtToken = jwtToken;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public JwtResponse() {
		
		
	}
	
	public JwtResponse(String jwtToken, String userName) {
		super();
		JwtToken = jwtToken;
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "JwtResponse [JwtToken=" + JwtToken + ", userName=" + userName + "]";
	}
	  
	

    private JwtResponse(Builder builder) {
        this.token = builder.token;
        this.type = builder.type;
        this.username = builder.username;
        this.roles = builder.roles;
    }

    public static class Builder {
        private String token;
        private String type;
        private String username;
        private List<String> roles;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder roles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public JwtResponse build() {
            return new JwtResponse(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }



	
	
	
	

}
