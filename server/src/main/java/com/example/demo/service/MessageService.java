package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Message;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository,
                          RoomRepository roomRepository,
                          UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public Page<Message> getMessages(Pageable pageable, UUID roomId) {
        Room r = new Room();
        r.setId(roomId);

        return this.messageRepository.findAllByRoom(r, pageable);
    }

    public String createMessage(String message, UUID roomId, UUID userId) {
        Room r = roomRepository.findById(roomId).orElseThrow(() -> new ApiException("ROOM_NOT_FOUND", "sfdasdfasdf"));
        User u = userRepository.findById(userId).orElseThrow(() -> new ApiException("USER_NOT_FOUND", "sfdasdfasdf"));

        Message m = new Message();
        m.setContent(message);
        m.setRoom(r);
        m.setSender(u);
        messageRepository.save(m);
        return "success";
    }
}
