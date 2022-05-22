package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.JwtTokenApi;
import ru.itis.dto.TokenCoupleDto;
import ru.itis.dto.response.AccountResponse;
import ru.itis.dto.response.TokenCoupleResponse;
import ru.itis.service.JwtTokenService;

@RequiredArgsConstructor
@RestController
public class JwtTokenController implements JwtTokenApi {

    private final JwtTokenService jwtTokenService;

    @Override
    public AccountResponse userInfoByToken(String token) {
        return jwtTokenService.getUserInfoByToken(token);
    }

    @Override
    public TokenCoupleResponse updateTokens(TokenCoupleDto tokenCoupleDto) {
        return jwtTokenService.refreshAccessToken(tokenCoupleDto);
    }

}

