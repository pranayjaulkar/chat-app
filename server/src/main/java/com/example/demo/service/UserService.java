package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    public List<String> validateUser(User user, String mode) {
        List<String> errors = new ArrayList<>();

        if (user.getEmail() == null || user.getEmail().isBlank())
            errors.add("'email' is required");

        if (user.getUsername() == null || user.getUsername().isBlank())
            errors.add("'username' is required");

        if (user.getFirstName() == null || user.getFirstName().isBlank())
            errors.add("'firstName' is required");

        if (user.getLastName() == null || user.getLastName().isBlank())
            errors.add("'lastName' is required");

        if (mode.equals("update")) {
            if (user.getId() == null)
                errors.add("'id' is required");
        }

        if (mode.equals("create")) {
            if (user.getPassword() == null || user.getPassword().isBlank())
                errors.add("'password' is required");
        }

        return errors;
    }
}
