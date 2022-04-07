package ru.itis.service;

import ru.itis.dto.request.RoomRequest;
import ru.itis.dto.response.RoomExtendedResponse;
import ru.itis.dto.response.RoomResponse;

import java.util.UUID;

public interface RoomService {
    RoomResponse createRoom(RoomRequest room);
    RoomExtendedResponse getRoomById(UUID roomId);
    void deleteRoom(UUID roomId);
    RoomExtendedResponse changeFilm(UUID roomId, UUID filmId);
}
