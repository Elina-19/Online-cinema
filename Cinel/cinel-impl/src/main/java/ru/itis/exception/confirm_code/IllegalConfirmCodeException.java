package ru.itis.exception.confirm_code;

import org.springframework.http.HttpStatus;
import ru.itis.exception.CinelServiceException;

public class IllegalConfirmCodeException extends CinelServiceException {

    public IllegalConfirmCodeException() {
        super(HttpStatus.UNAUTHORIZED, "Confirm code does not exist");
    }

}
