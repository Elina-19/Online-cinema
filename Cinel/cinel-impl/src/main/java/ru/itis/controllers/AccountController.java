package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.AccountApi;
import ru.itis.dto.request.AccountRequest;
import ru.itis.dto.response.TokenCoupleResponse;
import ru.itis.service.AccountService;
import ru.itis.service.JwtTokenService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class AccountController implements AccountApi {

    private final AccountService accountService;
    private final JwtTokenService tokenService;

    @Override
    public void joinToRoom(UUID accountId, String roomCode) {
        accountService.joinToRoom(accountId, roomCode);
    }

    @Override
    public void leaveRoom(UUID accountId, UUID roomId) {
        accountService.leftRoom(accountId, roomId);
    }

}
