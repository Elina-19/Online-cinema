package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.ConfirmRegistrationApi;
import ru.itis.service.SignUpService;

@RequiredArgsConstructor
@RestController
public class ConfirmRegistrationController implements ConfirmRegistrationApi {

    private final SignUpService signUpService;

    @Override
    public void confirm(String confirmCode) {
        signUpService.confirm(confirmCode);
    }
}
