package ru.itis.service;

import ru.itis.dto.response.AccountResponse;
import ru.itis.model.RefreshTokenEntity;

public interface AccountRefreshTokenService {

    RefreshTokenEntity generateRefreshToken(AccountResponse accountResponse);

    RefreshTokenEntity verifyRefreshTokenExpiryDate(String refreshToken);

}
