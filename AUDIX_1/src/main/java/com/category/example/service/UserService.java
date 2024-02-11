package com.category.example.service;

import com.category.example.repository.UserRepository ;
import com.category.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Example method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Example method to save a user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Example method to find a user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Add more business logic methods as needed
}
