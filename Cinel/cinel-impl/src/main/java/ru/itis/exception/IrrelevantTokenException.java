package ru.itis.exception;

import org.springframework.http.HttpStatus;

public class IrrelevantTokenException extends CinelServiceException{

    public IrrelevantTokenException(String token, String message) {
        super(HttpStatus.UNAUTHORIZED, String.format("Failed for [%s]: %s", token, message));
    }

}
