package ru.itis.exception;


import org.springframework.http.HttpStatus;

public class CinelNotFoundException extends CinelServiceException {

    public CinelNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
