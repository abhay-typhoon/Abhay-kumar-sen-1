package com.JWT.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JWT.service.user.User;

@Service
public class UserService implements UserDetailsService {

    private List<User> store = new ArrayList<>();
    private PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        // Initialize users with encoded passwords
        store.add(new User(UUID.randomUUID().toString(), "Abhay kumar sen", "abhay69123@gmail.com", passwordEncoder.encode("abc")));
        store.add(new User(UUID.randomUUID().toString(), "Shanky sen", "shanky@example.com", passwordEncoder.encode("abc")));
        store.add(new User(UUID.randomUUID().toString(), "Akshit sen", "akshit@example.com", passwordEncoder.encode("abc")));
    }

    public List<User> getUsers() {
        return this.store;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (User user : store) {
            if (user.getEmail().equals(username)) {
                // Convert your custom User to UserDetails
                return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword()) // Use encoded password from User
                    .authorities(getAuthorities("USER")) // Provide authorities
                    .build();
            }
        }
        throw new UsernameNotFoundException("User not found with email: " + username);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }
}
