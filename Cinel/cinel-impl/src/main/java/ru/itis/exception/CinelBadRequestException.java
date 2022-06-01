package ru.itis.exception;

import org.springframework.http.HttpStatus;

public class CinelBadRequestException extends CinelServiceException{

    public CinelBadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
