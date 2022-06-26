package ru.itis.exception;

import org.springframework.http.HttpStatus;

public class UploadFailException extends CinelFileStorageException {

    public UploadFailException(String message) {
        super(message);
    }
}
