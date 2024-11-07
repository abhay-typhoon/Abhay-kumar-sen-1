package com.JWT.Repository;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;


public class Krepository {
	
	public interface UserRepository extends JpaRepository<User, Long> {
	    Optional<User> findByUsername(String username);
	}

}
