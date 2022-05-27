package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.RoomApi;
import ru.itis.dto.response.RoomExtendedResponse;
import ru.itis.dto.response.RoomResponse;
import ru.itis.security.userdetails.AccountUserDetails;
import ru.itis.service.RoomService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class RoomController implements RoomApi<AccountUserDetails> {

    private final RoomService roomService;

    @Override
    public RoomResponse createRoom(@AuthenticationPrincipal AccountUserDetails account) {
        return roomService.createRoom(account.getId());
    }

    @Override
    public RoomExtendedResponse getRoom(UUID roomId) {
        return roomService.getRoomExtendedResponseById(roomId);
    }

    @Override
    public void deleteRoom(@AuthenticationPrincipal AccountUserDetails account, UUID roomId) {
        roomService.makeRoomInactive(account.getId(), roomId);
    }

    @Override
    public RoomExtendedResponse changeFilm(@AuthenticationPrincipal AccountUserDetails account, UUID roomId, UUID filmId) {
        return roomService.changeFilm(account.getId(), roomId, filmId);
    }
}
