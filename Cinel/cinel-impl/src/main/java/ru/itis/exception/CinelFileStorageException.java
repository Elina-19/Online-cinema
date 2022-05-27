package ru.itis.exception;

import org.springframework.http.HttpStatus;

public class CinelFileStorageException extends CinelServiceException {

    public CinelFileStorageException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}
