package com.example.demo.common;

import com.example.demo.model.RoomType;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RoomData {

    Optional<UUID> getId();

    String getName();

    RoomType getType();

    Set<UUID> getParticipants();
}
