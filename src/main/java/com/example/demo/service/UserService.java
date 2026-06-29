package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // REGISTER USER
    public User registerUser(User user) {

        Optional<User> existingUser =
                userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Encrypt Password
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }

    // LOGIN USER
    public User loginUser(String email, String password) {

        Optional<User> user =
                userRepository.findByEmail(email);

        if (user.isPresent()) {

            User existingUser = user.get();

            if (passwordEncoder.matches(
                    password,
                    existingUser.getPassword()
            )) {

                // Hide password before sending response
                existingUser.setPassword(null);

                return existingUser;
            }
        }

        throw new RuntimeException("Invalid Email or Password");
    }
}