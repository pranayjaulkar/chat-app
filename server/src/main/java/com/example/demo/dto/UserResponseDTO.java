package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
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
    private Instant createdAt;
    private Instant updatedAt;
    private UUID id;

    public UserResponseDTO(UUID id, String username, String firstName, String lastName, String fullName, String email, Instant createdAt, Instant updatedAt) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
    }


    public UserResponseDTO(User user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.id = user.getId();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
