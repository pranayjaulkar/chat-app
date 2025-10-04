package com.example.demo.dto;

import com.example.demo.model.Room;
import com.example.demo.model.RoomParticipant;
import com.example.demo.model.RoomType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@ToString
public class RoomResponse {
    private UUID id;
    private String name;
    private RoomType type;
    private UUID createdBy;
    private Instant createdAt;
    private Instant updatedAt;
    private Set<UserResponse> participants;

    public RoomResponse(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.type = room.getType();
        this.createdAt = room.getCreatedAt();
        this.createdBy = room.getCreatedBy();
        this.updatedAt = room.getUpdatedAt();
        this.participants = new HashSet<>();
        for (RoomParticipant rp : room.getParticipants()) {
            participants.add(new UserResponse(rp.getUser()));
        }
    }
}
