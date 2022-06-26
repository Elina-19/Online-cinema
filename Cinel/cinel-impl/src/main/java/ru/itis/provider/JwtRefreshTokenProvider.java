package ru.itis.provider;

import ru.itis.dto.enums.Role;
import ru.itis.dto.request.LoginRequest;
import ru.itis.dto.response.AccountResponse;
import ru.itis.model.RefreshTokenEntity;

public interface JwtRefreshTokenProvider {

    String generateRefreshToken(AccountResponse accountResponse);

    RefreshTokenEntity verifyRefreshTokenExpiration(String refreshToken);

}
