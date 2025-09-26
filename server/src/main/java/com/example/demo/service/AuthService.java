package com.example.demo.service;

import com.example.demo.dto.UserResponseDTO;
import com.example.demo.exception.ApiException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public UserResponseDTO login(HttpServletRequest request, User userData) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userData.getUsername(), userData.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext()
        );
        return userRepository.findUserByUsername(userData.getUsername());
    }

    public UserResponseDTO signupUser(User user) {
        Boolean userExists = userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail());

        if (!userExists) {
            List<String> errors = userService.validateUser(user, "create");

            if (!errors.isEmpty()) throw new ApiException("USER_VALIDATION_ERRORS", errors.toString());

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUsername(user.getUsername().trim());
            user.setFirstName(user.getFirstName().trim());
            user.setLastName(user.getLastName().trim());
            user.setEmail(user.getEmail().trim());
            user.setFullName(user.getFirstName() + " " + user.getLastName());

            User savedUser = userRepository.save(user);

            return new UserResponseDTO(savedUser);
        } else {
            throw new ApiException("USER_ALREADY_EXISTS", "User already exists with given email or username");
        }
    }

    public List<String> validateUser(User user) {
        List<String> errors = new ArrayList<>();

        if ((user.getEmail() == null || user.getEmail().isBlank()) && (user.getUsername() == null || user.getUsername().isBlank()))
            errors.add("'email' or 'username' is required");

        if (user.getPassword() == null || user.getPassword().isBlank()) errors.add("'password' is required");

        return errors;
    }
}
