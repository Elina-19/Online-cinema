package ru.itis.exception;

import java.util.UUID;

public class AccountNotExistException extends CinelNotExistException {
    public AccountNotExistException(UUID accountId){
        super("Account with id " + accountId + "does not exist");
    }
}
