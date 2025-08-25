package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public User login(HttpServletRequest request, @RequestBody User user) {
        String clientIp = request.getRemoteAddr();
        String method = request.getMethod();

        logger.info("client ip: {}", clientIp);
        logger.info("request method: {}", method);
        logger.info("Username: {}",user.getUsername());
        logger.info("Username: {}",user.getPassword());

        user.setId(UUID.randomUUID());
        logger.info("Username: {}",user.getId());
        return this.authService.login(user);
    }

}