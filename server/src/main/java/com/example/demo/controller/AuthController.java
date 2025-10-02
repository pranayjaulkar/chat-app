package com.example.demo.controller;

import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody User user, HttpServletRequest request) {
        List<String> errors = authService.validateUser(user);
        if (!errors.isEmpty()) throw new RuntimeException("VALIDATION_ERROR: " + errors.toString());
        return authService.login(request, user);
    }

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody User user) {
        return authService.signupUser(user);
    }

}