package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.service.CustomUserDetails;
import com.example.demo.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/rooms/{roomId}/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // get message of a room
    @GetMapping
    public Page<Message> getMessages(@RequestParam Integer page, @RequestParam Integer pageSize, @PathVariable UUID roomId) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdAt").descending());
        return messageService.getMessages(pageable, roomId);
    }

    @PostMapping
    public String createMessage(@PathVariable UUID roomId, @RequestBody String message, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return messageService.createMessage(message, roomId, userDetails.getUserId());
    }
}
