package com.UserManagment.userService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UserManagment.User;
import com.UserManagment.email.EmailService;
import com.UserManagment.repository.UserRepository;
import com.UserManagment.security.JwtUtility;
import com.UserManagment.security.PasswordHasher;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private JwtUtility jwtUtility;

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = messageDigest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashedBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    @Transactional
    public User registerUser(User user) {
        user.setPassword(PasswordHasher.hashPassword(user.getPassword()));
        user.setVerified(false);

        String verificationCode = UUID.randomUUID().toString();
        user.setVerificationCode(verificationCode);

        userRepository.save(user);

        String verificationLink = "http://localhost:8080/api/verify?email=" + verificationCode;
        emailService.sendEmail(user.getEmail(), "Verify your account", "Click here to verify: " + verificationLink);

        return user;
    }

    public boolean verifyUserByPassword(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (user.getPassword().equals(password)) {
                user.setVerified(true);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public String loginUser(String email, String password) throws NoSuchAlgorithmException {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String hashedPassword = hashPassword(password);
            if (hashedPassword.equals(user.getPassword())) {
                return jwtUtility.generateToken(email);
            }
        }
        throw new RuntimeException("Invalid credentials");
    }

    @Transactional
    public boolean forgotPassword(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String resetToken = UUID.randomUUID().toString();
            user.setResetToken(resetToken);
            userRepository.save(user);

            String resetLink = "http://localhost:8080/api/reset-password?token=" + resetToken;
            emailService.sendEmail(user.getEmail(), "Password Reset Request", "Click here to reset your password: " + resetLink);

            return true;
        }
        return false;
    }

    @Transactional
    public boolean resetPassword(String token, String newPassword) {
        Optional<User> userOpt = userRepository.findByResetToken(token);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(PasswordHasher.hashPassword(newPassword));
            user.setResetToken(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean verifyUser(String verificationCode) {
        Optional<User> userOpt = userRepository.findByVerificationCode(verificationCode);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (!user.isVerified()) {
                user.setVerified(true);
                user.setVerificationCode(null);
                userRepository.save(user);
            }
            return true;
        }
        throw new IllegalArgumentException("Invalid verification code");
    }

    @Transactional
    public boolean sendPasswordResetEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String resetToken = UUID.randomUUID().toString();
            user.setResetToken(resetToken);
            user.setResetTokenExpiry(convertLocalDateTimeToDate(LocalDateTime.now().plusHours(1)));
            userRepository.save(user);

            sendResetEmail(user.getEmail(), resetToken);
            return true;
        }
        return false;
    }

    private void sendResetEmail(String email, String resetToken) {
        String resetLink = "http://localhost:8080/AuthVerification/reset-password?token=" + resetToken;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the following link:\n" + resetLink);
        mailSender.send(message);
    }

    public boolean validateResetToken(String token) {
        Optional<User> optionalUser = userRepository.findByResetToken(token);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Date expiry = user.getResetTokenExpiry();
            return expiry.after(new Date());
        }
        return false;
    }

    private Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
