package com.UserManagment;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	        if (userService.verifyUser(email)) {
	            return ResponseEntity.ok("User verified.");
	        }
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification link.");
	    }

	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody Map<String, String> loginData) {
	        String email = loginData.get("email");
	        String password = loginData.get("password");
	        return ResponseEntity.ok(userService.loginUser(email, password));
	    }

}
