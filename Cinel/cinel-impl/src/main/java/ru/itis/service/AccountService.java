package ru.itis.service;

import ru.itis.dto.request.AccountRequest;
import ru.itis.dto.response.AccountResponse;
import ru.itis.model.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountService {

    Optional<AccountResponse> findByEmail(String email);

    Account getById(UUID id);

    Account save(Account account);

    void joinToRoom(UUID accountId, String roomCode);

    void leftRoom(UUID accountId, UUID roomId);

}
