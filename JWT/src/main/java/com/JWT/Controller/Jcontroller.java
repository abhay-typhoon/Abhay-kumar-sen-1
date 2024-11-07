package com.JWT.Controller;

import java.util.Optional;
import java.util.concurrent.ExecutorService;

import org.apache.catalina.User;
import org.apache.logging.slf4j.SLF4JProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JWT.Kuser;
import com.JWT.Service.Kservice;

@RestController
@RequestMapping("/api/auth")
public class Jcontroller {
	
	   @Autowired
	    private Kservice userService;

	    @Autowired
	    private SLF4JProvider jwtTokenProvider;

	    @PostMapping("/register")
	    public ResponseEntity<?> registerUser(@RequestBody Kuser kuser) {
	        if (userService.findByUsername(kuser.getUsername()).isPresent()) {
	            return ResponseEntity.badRequest().body("Username is already taken.");
	        }
	        userService.save(kuser);
	        return ResponseEntity.ok("User registered successfully");
	    }

	    @PostMapping("/login")
	    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
	        Optional<User> user = userService.findByUsername(loginRequest.getUsername());
	        if (user.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
	            String token = jwtTokenProvider.createToken(user.get().getUsername(), user.get().getRole());
	            return ResponseEntity.ok(new JwtResponse(token));
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    }

}
