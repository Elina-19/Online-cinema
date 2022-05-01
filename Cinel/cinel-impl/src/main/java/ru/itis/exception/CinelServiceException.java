package ru.itis.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CinelServiceException extends RuntimeException {

    private final HttpStatus httpStatus;

    public CinelServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
