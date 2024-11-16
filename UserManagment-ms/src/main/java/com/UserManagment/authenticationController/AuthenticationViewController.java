package com.UserManagment.authenticationController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.UserManagment.userService.UserService;

@Controller
@RequestMapping("/AuthVerification")
public class AuthenticationViewController {

    @Autowired
    private UserService userService;

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        // Add the token to the model to be used in the Thymeleaf template
        model.addAttribute("token", token);
        return "reset-password-form"; // This should match the name of your Thymeleaf template
    }
}
