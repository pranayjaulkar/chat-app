package com.example.demo.controller;

import com.example.demo.dto.CreateRoomData;
import com.example.demo.dto.CreateRoomResponse;
import com.example.demo.model.Room;
import com.example.demo.service.CustomUserDetails;
import com.example.demo.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;
    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);


    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public Page<Room> getAllRooms(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam Integer page, @RequestParam Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return roomService.getRoomsByUser(userDetails.getUserId(), pageable);
    }

    @PostMapping
    public CreateRoomResponse createRoom(@RequestBody CreateRoomData room, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return roomService.createRoom(room, userDetails.getUserId());
    }
}