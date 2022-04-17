package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.AccountRequest;
import ru.itis.exception.AccountNotExistException;
import ru.itis.model.Account;
import ru.itis.model.Room;
import ru.itis.repository.AccountRepository;
import ru.itis.service.AccountService;
import ru.itis.service.RoomService;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private RoomService roomService;

    @Autowired
    public void setRoomService(RoomService roomService){
        this.roomService = roomService;
    }

    @Override
    public Account getById(UUID id) {
        return accountRepository
                .findById(id)
                .orElseThrow(()
                        -> new AccountNotExistException(id));
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void joinToRoom(UUID accountId, AccountRequest accountRequest) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(()
                        -> new AccountNotExistException(accountId));

        Room room = roomService
                .getRoomByCode(
                accountRequest.getRoomCode());

        account.getRooms().add(room);
        accountRepository.save(account);
    }

    @Override
    public void leftRoom(UUID accountId, UUID roomId) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(()
                        -> new AccountNotExistException(accountId));

        Room room = roomService.getRoomById(roomId);

        account.getRooms().remove(room);
        accountRepository.save(account);
    }
}
