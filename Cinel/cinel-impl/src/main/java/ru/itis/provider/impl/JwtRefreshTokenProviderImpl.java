package ru.itis.provider.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.dto.enums.Role;
import ru.itis.dto.request.LoginRequest;
import ru.itis.dto.response.AccountResponse;
import ru.itis.model.RefreshTokenEntity;
import ru.itis.provider.JwtRefreshTokenProvider;
import ru.itis.service.AccountRefreshTokenService;
import ru.itis.service.impl.JwtTokenServiceImpl;

@RequiredArgsConstructor
@Component
public class JwtRefreshTokenProviderImpl implements JwtRefreshTokenProvider {

    private final AccountRefreshTokenService accountRefreshTokenService;

    @Override
    public String generateRefreshToken(AccountResponse accountResponse) {
        return String.valueOf(accountRefreshTokenService
                .generateRefreshToken(accountResponse).getId());
    }

    @Override
    public RefreshTokenEntity verifyRefreshTokenExpiration(String refreshToken) {
        return accountRefreshTokenService.verifyRefreshTokenExpiryDate(refreshToken);
    }
}
