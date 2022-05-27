package ru.itis.exception;

import org.springframework.http.HttpStatus;

public class CinelUnauthorizedException extends CinelServiceException{

    public CinelUnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

}
