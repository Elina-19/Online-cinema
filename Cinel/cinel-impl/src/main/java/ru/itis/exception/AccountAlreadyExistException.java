package ru.itis.exception;

public class AccountAlreadyExistException extends CinelBadRequestException{

    public AccountAlreadyExistException() {
        super("Account with that email or username already exists");
    }

}
