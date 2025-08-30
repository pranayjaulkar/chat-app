package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class UserResponseDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private UUID id;

    public UserResponseDTO(String username, String firstName, String lastName, String fullName, String email, UUID id) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.email = email;
        this.id = id;
    }


    public UserResponseDTO(User user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.id = user.getId();
    }
}
