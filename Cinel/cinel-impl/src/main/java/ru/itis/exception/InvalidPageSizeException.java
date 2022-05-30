package ru.itis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPageSizeException extends CinelServiceException {

    public InvalidPageSizeException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
