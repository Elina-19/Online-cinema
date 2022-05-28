package ru.itis.exception.token;

import org.springframework.http.HttpStatus;
import ru.itis.exception.CinelServiceException;

public class IrrelevantTokenException extends CinelServiceException {

    public IrrelevantTokenException(String token, String message) {
        super(HttpStatus.UNAUTHORIZED, String.format("Failed for [%s]: %s", token, message));
    }

}
