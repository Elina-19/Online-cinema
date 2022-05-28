package ru.itis.exception.token;

import org.springframework.http.HttpStatus;
import ru.itis.exception.CinelServiceException;

public class TokenRefreshException extends CinelServiceException {

    public TokenRefreshException(String token, String message) {
        super(HttpStatus.UNAUTHORIZED, String.format("Failed for [%s]: %s", token, message));
    }

}

