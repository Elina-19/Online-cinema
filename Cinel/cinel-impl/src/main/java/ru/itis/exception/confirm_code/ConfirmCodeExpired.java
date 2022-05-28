package ru.itis.exception.confirm_code;

import org.springframework.http.HttpStatus;
import ru.itis.exception.CinelServiceException;

public class ConfirmCodeExpired extends CinelServiceException {

    public ConfirmCodeExpired() {
        super(HttpStatus.UNAUTHORIZED, "Confirm code expired");
    }

}
