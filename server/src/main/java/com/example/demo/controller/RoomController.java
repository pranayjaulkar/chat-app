package com.example.demo.controller;

import com.example.demo.dto.RoomCreateData;
import com.example.demo.exception.ApiException;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.CustomUserDetails;
import com.example.demo.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomRepository roomRepository;
    private final RoomService roomService;
    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);


    public RoomController(RoomRepository roomRepository, RoomService roomService) {
        this.roomRepository = roomRepository;
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return roomRepository.findByUser(userDetails.getUserId());
    }

    @PostMapping
    public Room createRoom(@RequestBody RoomCreateData room) {
        logger.info(room.toString());
        List<String> errors = roomService.validateRoom(room);
        if (!errors.isEmpty()) throw new ApiException("ROOM_VALIDATION_ERROR", errors.toString());
//        return roomRepository.save();
    }
}