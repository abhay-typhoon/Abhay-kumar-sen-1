package com.UserManagment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserManagment.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByVerificationCode(String code);
    Optional<User> findByResetToken(String resetToken);
    
}
