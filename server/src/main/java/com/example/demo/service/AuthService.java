package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository userRepo;

    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    private User findUser(String username) {
        return userRepo.findByUsername(username);
    }

    private Boolean findUserExists(UUID id) {
        return userRepo.existsById(id);
    }

    private User createUser(User user) {
        return userRepo.save(user);
    }

    public User login(User user) {
        Boolean userExists = this.findUserExists(user.getId());
        if (!userExists) {
            return this.createUser(user);
        }
        else {
            throw new UserNotFoundException("User not found with id " + user.getId());
        }
    }
}
