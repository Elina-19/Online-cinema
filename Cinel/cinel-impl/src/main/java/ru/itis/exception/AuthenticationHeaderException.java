package ru.itis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationHeaderException extends AuthenticationException {

    public AuthenticationHeaderException(String msg) {
        super(msg);
    }

    public AuthenticationHeaderException(String msg, Throwable t) {
        super(msg, t);
    }
}
