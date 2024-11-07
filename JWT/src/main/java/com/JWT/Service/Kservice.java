package com.JWT.Service;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JWT.Kuser;
import com.JWT.Repository.Krepository.UserRepository;

@Service
public class Kservice {
	
	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    public User save(Kuser kuser) {
	        kuser.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepository.save(kuser);
	    }

	    public Optional<User> findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }

}
