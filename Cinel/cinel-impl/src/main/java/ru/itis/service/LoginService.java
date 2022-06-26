package ru.itis.service;

import ru.itis.dto.request.LoginRequest;
import ru.itis.dto.response.TokenCoupleResponse;

public interface LoginService {

    TokenCoupleResponse login(LoginRequest loginRequest);

}
