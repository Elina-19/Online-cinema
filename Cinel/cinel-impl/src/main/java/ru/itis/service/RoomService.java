package ru.itis.service;

import ru.itis.dto.request.MessageRequest;
import ru.itis.dto.response.MessageResponse;
import ru.itis.dto.response.RoomExtendedResponse;
import ru.itis.dto.response.RoomResponse;
import ru.itis.model.Room;

import java.util.UUID;

public interface RoomService {

    RoomResponse createRoom(UUID accountId);

    RoomExtendedResponse getRoomExtendedResponseById(UUID roomId);

    Room getRoomById(UUID roomId);

    void makeRoomInactive(UUID accountId, UUID roomId);

    RoomExtendedResponse changeFilm(UUID accountId, UUID roomId, UUID filmId);

    Room getRoomByCode(String code);

    MessageResponse sendMessageToRoom(UUID roomId, MessageRequest message);

}
