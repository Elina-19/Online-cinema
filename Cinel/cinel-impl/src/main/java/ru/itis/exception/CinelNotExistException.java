package ru.itis.exception;

import org.springframework.http.HttpStatus;

public class CinelNotExistException extends CinelServiceException{
    public CinelNotExistException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
