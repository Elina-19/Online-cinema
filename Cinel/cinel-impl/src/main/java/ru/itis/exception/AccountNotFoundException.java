package ru.itis.exception;

public class AccountNotFoundException extends CinelNotFoundException{

    public AccountNotFoundException() {
        super("Account not found");
    }

}
