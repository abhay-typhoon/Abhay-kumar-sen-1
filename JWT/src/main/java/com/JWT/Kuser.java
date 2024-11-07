package com.JWT;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Kuser {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(unique = true)
	    private String username;

	    @Override
		public String toString() {
			return "Kuser [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
		}

		public Kuser(Long id, String username, String password, String role) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.role = role;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		private String password;
	    
	    private String role; 
	    
	    public Kuser() {
	    	
	    	
	    }

}
