package ru.itis.exception;

import org.springframework.http.HttpStatus;

public class TokenRefreshException extends CinelServiceException {

    public TokenRefreshException(String token, String message) {
        super(HttpStatus.UNAUTHORIZED, String.format("Failed for [%s]: %s", token, message));
    }

}

