package com.example.demo.controller;

import com.example.demo.dto.CreateRoomData;
import com.example.demo.dto.RoomResponse;
import com.example.demo.dto.UpdateRoomData;
import com.example.demo.model.Room;
import com.example.demo.service.CustomUserDetails;
import com.example.demo.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;


@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;
    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);


    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    //    get rooms of user
    @GetMapping
    public Page<Room> getAllRooms(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam Integer page, @RequestParam Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return roomService.getRoomsByUser(userDetails.getUserId(), pageable);
    }

    //    create room
    @PostMapping
    public RoomResponse createRoom(@RequestBody CreateRoomData room, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return roomService.createRoom(room, userDetails.getUserId());
    }

    //    update room
    @PatchMapping("/{roomId}")
    public RoomResponse updateRoom(@RequestBody UpdateRoomData room, @AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable UUID roomId) {
        return roomService.updateRoom(room, roomId);
    }
}