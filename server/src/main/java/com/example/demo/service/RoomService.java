package com.example.demo.service;

import com.example.demo.dto.CreateRoomData;
import com.example.demo.dto.CreateRoomResponse;
import com.example.demo.exception.ApiException;
import com.example.demo.model.Room;
import com.example.demo.model.RoomParticipant;
import com.example.demo.model.RoomType;
import com.example.demo.model.User;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);

    public RoomService(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public List<String> validateRoom(CreateRoomData room) {
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
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException("USER_NOT_FOUND", "user not found with ID: " + userId));
        return roomRepository.findRoomsByUser(user, pageable);
    }

    public CreateRoomResponse createRoom(CreateRoomData roomData, UUID userId) {
        List<String> errors = this.validateRoom(roomData);
        if (!errors.isEmpty()) throw new ApiException("ROOM_VALIDATION_ERROR", errors.toString());
        Room room = new Room();
        room.setName(roomData.getName());
        room.setType(roomData.getType());
        room.setCreatedBy(userId);
        Set<RoomParticipant> participants = new HashSet<>();
        roomData.getParticipants().add(userId);

        for (UUID participantId : roomData.getParticipants()) {
            User user = userRepository.findById(participantId).orElseThrow(() -> new ApiException("USER_NOT_FOUND", "user not found with ID: " + userId.toString()));
            RoomParticipant participant = new RoomParticipant();
            participant.setUser(user);
            participant.setRoom(room);
            participants.add(participant);
        }

        room.setParticipants(participants);

        return new CreateRoomResponse(roomRepository.save(room));
    }

}
