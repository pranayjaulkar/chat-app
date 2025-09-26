package com.example.demo.dto;

import com.example.demo.model.Room;
import com.example.demo.model.RoomParticipant;
import com.example.demo.model.RoomType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class RoomCreateData {
    private String name;
    private RoomType type;
    private Set<RoomParticipant> participants;

    public RoomCreateData(String name, RoomType type, Set<RoomParticipant> participants) {
        this.name = name;
        this.type = type;
        this.participants = participants;
    }


    public RoomCreateData(Room room) {
        this.name = room.getName();
        this.type = room.getType();
        this.participants = room.getParticipants();
    }
}
