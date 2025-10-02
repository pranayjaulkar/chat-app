package com.example.demo.repository;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    @Query("SELECT r FROM RoomParticipant rp JOIN rp.room r WHERE rp.user = :user")
    Page<Room> findRoomsByUser(@Param("user") User user, Pageable pageable);
}
