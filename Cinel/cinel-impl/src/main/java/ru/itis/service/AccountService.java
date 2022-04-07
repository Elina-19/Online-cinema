package ru.itis.service;

import ru.itis.dto.request.AccountRequest;

import java.util.UUID;

public interface AccountService {
    void joinToRoom(UUID accountId, AccountRequest accountRequest);
    void leftRoom(UUID accountId, UUID roomId);
}
