package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.RoomApi;
import ru.itis.dto.request.RoomRequest;
import ru.itis.dto.response.RoomExtendedResponse;
import ru.itis.dto.response.RoomResponse;
import ru.itis.service.RoomService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class RoomController implements RoomApi {

    private final RoomService roomService;

    @Override
    public RoomResponse createRoom(RoomRequest room) {
        return roomService.createRoom(room);
    }

    @Override
    public RoomExtendedResponse getRoom(UUID roomId) {
        return roomService.getRoomById(roomId);
    }

    @Override
    public void deleteRoom(UUID roomId) {
        roomService.deleteRoom(roomId);
    }

    @Override
    public RoomExtendedResponse changeFilm(UUID roomId, UUID filmId) {
        return roomService.changeFilm(roomId, filmId);
    }
}
