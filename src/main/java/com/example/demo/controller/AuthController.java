package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService userService;

    // REGISTER API
    @PostMapping("/register")
    public User register(@RequestBody User user) {

        // Default role
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        return userService.registerUser(user);
    }

    // LOGIN API
    @PostMapping("/login")
    public User login(@RequestBody User user) {

        return userService.loginUser(
                user.getEmail(),
                user.getPassword()
        );
    }
}