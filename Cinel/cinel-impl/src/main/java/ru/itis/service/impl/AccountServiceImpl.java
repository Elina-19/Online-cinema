package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.AccountRequest;
import ru.itis.exception.AccountNotExistException;
import ru.itis.exception.RoomNotExistException;
import ru.itis.exception.RoomNotFoundException;
import ru.itis.model.Account;
import ru.itis.model.Room;
import ru.itis.repository.AccountRepository;
import ru.itis.repository.RoomRepository;
import ru.itis.service.AccountService;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoomRepository roomRepository;

    @Override
    public void joinToRoom(UUID accountId, AccountRequest accountRequest) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(()
                        -> new AccountNotExistException(accountId));

        Room room = roomRepository
                .findRoomByCode(accountRequest.getRoomCode())
                .orElseThrow(RoomNotFoundException::new);

        account.getRooms().add(room);
        accountRepository.save(account);
    }

    @Override
    public void leftRoom(UUID accountId, UUID roomId) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(()
                        -> new AccountNotExistException(accountId));

        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(()
                        -> new RoomNotExistException(roomId));

        account.getRooms().remove(room);

        accountRepository.save(account);
    }
}
