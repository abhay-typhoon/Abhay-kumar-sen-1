package com.UserManagment.authenticationController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UserManagment.userService.UserService;

@Controller
@RequestMapping("/AuthVerification")
public class AuthenticationController {
	


    @Autowired
    private UserService userService;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        // Call service method to generate reset token and send email
        boolean isEmailSent = userService.sendPasswordResetEmail(email);

        if (isEmailSent) {
            return ResponseEntity.ok("Password reset email sent.");
        } else {
            return ResponseEntity.status(404).body("Email not found.");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        boolean isValidToken = userService.validateResetToken(token);

        if (isValidToken) {
            boolean isPasswordReset = userService.resetPassword(token, newPassword);
            if (isPasswordReset) {
                return ResponseEntity.ok("Password reset successfully.");
            } else {
                return ResponseEntity.status(400).body("Failed to reset password.");
            }
        } else {
            return ResponseEntity.status(400).body("Invalid or expired token.");
        }
    }
}
