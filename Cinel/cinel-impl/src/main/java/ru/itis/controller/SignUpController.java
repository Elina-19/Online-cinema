package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.SignUpApi;
import ru.itis.dto.request.SignUpRequest;
import ru.itis.service.SignUpService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class SignUpController implements SignUpApi {

    private final SignUpService signUpService;

    @Override
    public UUID signUp(SignUpRequest signUpRequest) {
        return signUpService.signUp(signUpRequest);
    }

}
