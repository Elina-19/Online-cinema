package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.AccountRequest;
import ru.itis.dto.response.AccountResponse;
import ru.itis.exception.AccountNotExistException;
import ru.itis.model.Account;
import ru.itis.model.Room;
import ru.itis.repository.AccountRepository;
import ru.itis.service.AccountService;
import ru.itis.service.RoomService;
import ru.itis.utils.mapper.AccountMapper;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private RoomService roomService;

    private final AccountMapper accountMapper;

    @Autowired
    public void setRoomService(RoomService roomService){
        this.roomService = roomService;
    }

    @Override
    public Optional<AccountResponse> findByEmail(String email) {
        return accountRepository
                .findByEmail(email)
                .map(accountMapper::toResponse);
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
    public void joinToRoom(UUID accountId, String roomCode) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(()
                        -> new AccountNotExistException(accountId));

        Room room = roomService.getRoomByCode(roomCode);

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
