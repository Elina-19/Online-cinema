package ru.itis.service;

import ru.itis.dto.TokenCoupleDto;
import ru.itis.dto.response.AccountResponse;
import ru.itis.dto.response.TokenCoupleResponse;
import ru.itis.model.Account;
import ru.itis.model.RefreshTokenEntity;

public interface JwtTokenService {

    AccountResponse getUserInfoByToken(String token);

    Account getUserByToken(String token);

    TokenCoupleResponse refreshAccessToken(TokenCoupleDto tokenCoupleDto);

    TokenCoupleResponse generateTokenCouple(AccountResponse accountResponse);

    boolean validateAccessToken(String accessToken);

}
