package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.AccountApi;
import ru.itis.dto.request.AccountRequest;
import ru.itis.service.AccountService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class AccountController implements AccountApi {

    private final AccountService accountService;

    @Override
    public void joinToRoom(UUID accountId, AccountRequest account) {
        accountService.joinToRoom(accountId, account);
    }

    @Override
    public void leftRoom(UUID accountId, UUID roomId) {
        accountService.leftRoom(accountId, roomId);
    }
}
