package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.AccountApi;
import ru.itis.security.userdetails.AccountUserDetails;
import ru.itis.service.AccountService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class AccountController implements AccountApi<AccountUserDetails> {

    private final AccountService accountService;

    @Override
    public void joinToRoom(@AuthenticationPrincipal AccountUserDetails account, String roomCode) {
        accountService.joinToRoom(account.getId(), roomCode);
    }

    @Override
    public void leaveRoom(@AuthenticationPrincipal AccountUserDetails account, UUID roomId) {
        accountService.leftRoom(account.getId(), roomId);
    }

}
