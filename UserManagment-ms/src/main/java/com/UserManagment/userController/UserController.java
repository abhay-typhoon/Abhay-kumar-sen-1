package com.UserManagment.userController;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UserManagment.User;
import com.UserManagment.VerificationRequest;
import com.UserManagment.userService.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	 @Autowired
	    private UserService userService;

	    @PostMapping("/register")
	   
	    public ResponseEntity<String> register(@RequestBody User user) {
	        userService.registerUser(user);
	        return ResponseEntity.ok("User registered. Please check email to verify.");
	    }

	    @GetMapping("/verify")
	    public ResponseEntity<String> verifyUser(@RequestParam String email) {
	        try {
	            boolean isVerified = userService.verifyUser(email);
	            if (isVerified) {
	                return ResponseEntity.ok("User verified successfully!");
	            } else {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification failed!");
	            }
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
	        }
	    }


	    @PostMapping("/login")
	    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginData) {
	        String email = loginData.get("email");
	        String password = loginData.get("password");

	        try {
	            String token = userService.loginUser(email, password);
	            Map<String, String> response = Map.of("token", token);
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
	        }
	    }

	    

}
