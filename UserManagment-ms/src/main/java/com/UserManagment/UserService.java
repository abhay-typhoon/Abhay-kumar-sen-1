package com.UserManagment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserManagment.Email.EmailService;
import com.UserManagment.JWT.JwtUtility;
import com.UserManagment.Password.PasswordHasher;
import com.UserManagment.Repository.UserRepository;

@Service
public class UserService {
	
	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private EmailService emailService;

	    @Autowired
	    private JwtUtility jwtUtility;

	    public User registerUser(User user) {
	        user.setPassword(PasswordHasher.hashPassword(user.getPassword()));
	        user.setVerified(false);
	        userRepository.save(user);
	        
	        String verificationLink = "http://localhost:8080/api/verify?email=" + user.getEmail();
	        emailService.sendEmail(user.getEmail(), "Verify your account", "Click here to verify: " + verificationLink);
	        
	        return user;
	    }
	    
	    public boolean verifyUser(String email) {
	        Optional<User> userOpt = userRepository.findByEmail(email);
	        if (userOpt.isPresent()) {
	            User user = userOpt.get();
	            user.setVerified(true);
	            userRepository.save(user);
	            return true;
	        }
	        return false;
	    }
	    
	    public String loginUser(String email, String password) {
	        Optional<User> userOpt = userRepository.findByEmail(email);
	        if (userOpt.isPresent() && PasswordHasher.hashPassword(password).equals(userOpt.get().getPassword())) {
	            return jwtUtility.generateToken(email);
	        }
	        throw new RuntimeException("Invalid credentials");
	    }

}
