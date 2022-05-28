package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.LoginApi;
import ru.itis.dto.request.LoginRequest;
import ru.itis.dto.response.TokenCoupleResponse;
import ru.itis.service.LoginService;

@RequiredArgsConstructor
@RestController
public class LoginController implements LoginApi {

    private final LoginService loginService;

    @Override
    public TokenCoupleResponse login(LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

}
