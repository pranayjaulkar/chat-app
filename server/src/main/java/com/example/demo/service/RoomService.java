package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Room;
import com.example.demo.model.RoomType;
import com.example.demo.model.User;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public RoomService(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public List<String> validateRoom(Room room) {
        List<String> errors = new ArrayList<>();

        if (room == null) {
            errors.add("room data is required");
            return errors;
        }

        if (room.getName() == null || room.getName().isBlank()) errors.add("'name' is required");

        if (room.getType() == null || !EnumSet.allOf(RoomType.class).stream().map(Enum::name).toList().contains(room.getType().name()))
            errors.add("'type' is required");

        if (room.getParticipants() == null || room.getParticipants().isEmpty())
            errors.add("'participants' is required");

        return errors;
    }

    public Page<Room> getRoomsByUser(UUID userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException("USER_NOT_FOUND", "user not found with ID: " + userId.toString()));
        return roomRepository.findRoomsByUser(user, pageable);
    }

    public Room createRoom(Room room, UUID userId) {
        List<String> errors = this.validateRoom(room);
        if (!errors.isEmpty()) throw new ApiException("ROOM_VALIDATION_ERROR", errors.toString());
        room.setCreatedBy(userId);
//        room.setParticipants(room.getParticipants().add(userId));
        return roomRepository.save(room);
    }

}
