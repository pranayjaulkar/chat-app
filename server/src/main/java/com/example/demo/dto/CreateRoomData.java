package com.example.demo.dto;

import com.example.demo.model.RoomType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@ToString
public class CreateRoomData {
    private String name;
    private RoomType type;
    private Set<UUID> participants;
}
