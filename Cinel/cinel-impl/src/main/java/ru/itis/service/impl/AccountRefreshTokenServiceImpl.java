package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.dto.response.AccountResponse;
import ru.itis.exception.AccountNotFoundException;
import ru.itis.exception.TokenRefreshException;
import ru.itis.model.AccountRefreshTokenEntity;
import ru.itis.model.RefreshTokenEntity;
import ru.itis.repository.AccountRefreshTokenRepository;
import ru.itis.repository.AccountRepository;
import ru.itis.service.AccountRefreshTokenService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountRefreshTokenServiceImpl implements AccountRefreshTokenService {

    @Value("${jwt.expiration.refresh.mills}")
    private long expirationRefreshInMills;

    private final AccountRefreshTokenRepository refreshTokenRepository;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public AccountRefreshTokenEntity generateRefreshToken(AccountResponse accountResponse) {
        return refreshTokenRepository.save(
                AccountRefreshTokenEntity.builder()
                        .expiryDate(Instant.now().plusMillis(expirationRefreshInMills))
                        .account(accountRepository
                                .findByEmail(accountResponse.getEmail())
                                .orElseThrow(AccountNotFoundException::new))
                        .build()
        );
    }

    @Override
    public RefreshTokenEntity verifyRefreshTokenExpiryDate(String refreshToken) {
        return refreshTokenRepository.findById(UUID.fromString(refreshToken)).map(token -> {
            refreshTokenRepository.delete(token);

            if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
                throw new TokenRefreshException(String.valueOf(token.getId()), "Срок действия токена обновления истек.");
            }

            return refreshTokenRepository.save(
                    AccountRefreshTokenEntity.builder()
                            .expiryDate(Instant.now().plusMillis(expirationRefreshInMills))
                            .account(token.getAccount())
                            .build());
        }).orElseThrow(() -> {
            throw new TokenRefreshException(refreshToken, "Токен не существует.");
        });
    }
}
