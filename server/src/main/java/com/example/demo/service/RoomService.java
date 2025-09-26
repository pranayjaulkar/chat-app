package com.example.demo.service;

import com.example.demo.dto.RoomCreateData;
import com.example.demo.model.Room;
import com.example.demo.model.RoomType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    public List<String> validateRoom(RoomCreateData room) {
        List<String> errors = new ArrayList<>();

        if (room == null) {
            errors.add("room data is required");
            return errors;
        }

        if (room.getName() == null || room.getName().isBlank())
            errors.add("'name' is required");

        if (room.getType() == null || !EnumSet.allOf(RoomType.class).stream()
                .map(Enum::name)
                .toList()
                .contains(room.getType().name()))
            errors.add("'type' is required");

        if (room.getParticipants() == null || room.getParticipants().isEmpty())
            errors.add("'participants' is required");

        return errors;
    }
}
