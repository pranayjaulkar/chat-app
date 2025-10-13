package com.example.demo.service;

import com.example.demo.common.RoomData;
import com.example.demo.dto.CreateRoomData;
import com.example.demo.dto.RoomResponse;
import com.example.demo.dto.UpdateRoomData;
import com.example.demo.exception.ApiException;
import com.example.demo.model.Room;
import com.example.demo.model.RoomParticipant;
import com.example.demo.model.RoomType;
import com.example.demo.model.User;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
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

    public List<String> validateRoom(RoomData room) {
        List<String> errors = new ArrayList<>();

        if (room == null) {
            errors.add("room data is required");
            return errors;
        }

        if (room instanceof UpdateRoomData && room.getId().isEmpty()) {
            errors.add("'id' is required");
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

    public RoomResponse createRoom(CreateRoomData roomData, UUID userId) {
        List<String> errors = validateRoom(roomData);
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

        return new RoomResponse(roomRepository.save(room));
    }

    public RoomResponse updateRoom(UpdateRoomData roomData, UUID roomId) {

        List<String> errors = validateRoom(roomData);
        if (!errors.isEmpty()) throw new ApiException("ROOM_VALIDATION_ERROR", errors.toString());

        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ApiException("ROOM_NOT_FOUND", "Room not found with ID: " + roomId));

        room.setName(roomData.getName());
        room.setType(roomData.getType());


        Set<UUID> newParticipantIds = new HashSet<>(roomData.getParticipants());
        Set<UUID> existingParticipantIds = new HashSet<>();

        for (RoomParticipant rp : room.getParticipants()) {
            existingParticipantIds.add(rp.getUser().getId());
        }

        Set<UUID> toAdd = new HashSet<>(newParticipantIds);
        toAdd.removeAll(existingParticipantIds);

        Set<UUID> toRemove = new HashSet<>(existingParticipantIds);
        toRemove.removeAll(newParticipantIds);

        if (!toRemove.isEmpty()) {
            room.getParticipants().removeIf(rp -> toRemove.contains(rp.getUser().getId()));
        }

        if (!toAdd.isEmpty()) {
            List<User> newUsers = userRepository.findAllById(toAdd);
            logger.info("new Users {}", newUsers);

            if (newUsers.size() != toAdd.size()) {
                throw new ApiException("USER_NOT_FOUND", "Some participant IDs were not found in database");
            }

            for (User user : newUsers) {
                RoomParticipant participant = new RoomParticipant();
                participant.setUser(user);
                participant.setRoom(room);
                room.getParticipants().add(participant);
            }
        }


        Room updatedRoom = roomRepository.save(room);
        return new RoomResponse(updatedRoom);
    }


}
