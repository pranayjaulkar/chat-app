package com.example.demo.repository;

import com.example.demo.dto.UserResponseDTO;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByUsernameOrEmail(String username, String email);


    @Query("SELECT new com.example.demo.dto.UserResponseDTO(u) FROM User u WHERE u.username = :username")
    UserResponseDTO findUserByUsername(@Param("username") String username);
}
