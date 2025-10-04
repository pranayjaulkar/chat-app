package com.example.demo.dto;

import com.example.demo.common.RoomData;
import com.example.demo.model.RoomType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@ToString
public class UpdateRoomData implements RoomData {
    private UUID id;
    private String name;
    private RoomType type;
    private Set<UUID> participants;

    @Override
    public Optional<UUID> getId() {
        return Optional.ofNullable(id);
    }
}
