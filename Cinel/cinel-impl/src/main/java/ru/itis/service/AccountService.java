package ru.itis.service;

import ru.itis.dto.request.AccountRequest;
import ru.itis.model.Account;

import java.util.UUID;

public interface AccountService {

    Account getById(UUID id);

    Account save(Account account);

    void joinToRoom(UUID accountId, AccountRequest accountRequest);

    void leftRoom(UUID accountId, UUID roomId);
}
