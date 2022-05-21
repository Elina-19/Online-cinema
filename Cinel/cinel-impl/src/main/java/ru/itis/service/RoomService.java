package ru.itis.service;

import ru.itis.dto.request.RoomRequest;
import ru.itis.dto.response.RoomExtendedResponse;
import ru.itis.dto.response.RoomResponse;
import ru.itis.model.Room;

import java.util.UUID;

public interface RoomService {

    RoomResponse createRoom(RoomRequest room);

    RoomExtendedResponse getRoomExtendedResponseById(UUID roomId);

    Room getRoomById(UUID roomId);

    void makeRoomInactive(UUID roomId);

    RoomExtendedResponse changeFilm(UUID roomId, UUID filmId);

    Room getRoomByCode(String code);
}
