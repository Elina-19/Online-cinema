package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.itis.consts.CinelConstants;
import ru.itis.dto.TokenCoupleDto;
import ru.itis.dto.enums.Role;
import ru.itis.dto.request.LoginRequest;
import ru.itis.dto.response.AccountResponse;
import ru.itis.dto.response.TokenCoupleResponse;
import ru.itis.model.RefreshTokenEntity;
import ru.itis.provider.JwtAccessTokenProvider;
import ru.itis.provider.JwtRefreshTokenProvider;
import ru.itis.service.JwtTokenService;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;
    private final JwtRefreshTokenProvider jwtRefreshTokenProvider;

    @Override
    public AccountResponse getUserInfoByToken(String token) {
        return jwtAccessTokenProvider.userInfoByToken(token);
    }

    @Override
    public TokenCoupleResponse generateTokenCouple(AccountResponse accountResponse) {
        String accessToken = jwtAccessTokenProvider.generateAccessToken(
                accountResponse.getEmail(),
                Collections.singletonMap(CinelConstants.ROLE, accountResponse.getRole())
        );

        String refreshToken = jwtRefreshTokenProvider.generateRefreshToken(accountResponse);

        return TokenCoupleResponse.builder()
                .accessToken(CinelConstants.BEARER.concat(StringUtils.SPACE).concat(accessToken))
                .refreshToken(refreshToken)
                .accessTokenExpirationDate(jwtAccessTokenProvider.getExpirationDateFromAccessToken(accessToken))
                .build();
    }

    @Override
    public boolean validateAccessToken(String accessToken) {
        return jwtAccessTokenProvider.validateAccessToken(accessToken);
    }

    @Override
    public TokenCoupleResponse refreshAccessToken(TokenCoupleDto tokenCoupleDto) {
        String role = jwtAccessTokenProvider.getRoleFromAccessToken(
                tokenCoupleDto.getAccessToken()
                .replace(CinelConstants.BEARER.concat(StringUtils.SPACE), StringUtils.EMPTY));

        RefreshTokenEntity verifiedRefreshToken = jwtRefreshTokenProvider.verifyRefreshTokenExpiration(
                tokenCoupleDto.getRefreshToken());

        String accessToken = jwtAccessTokenProvider.generateAccessToken(
                jwtAccessTokenProvider.getSubjectFromAccessToken(
                    tokenCoupleDto.getAccessToken()
                    .replace(CinelConstants.BEARER.concat(StringUtils.SPACE), StringUtils.EMPTY)),
                Collections.singletonMap(CinelConstants.ROLE, role));

        return TokenCoupleResponse.builder()
                .refreshToken(String.valueOf(verifiedRefreshToken.getId()))
                .accessToken(CinelConstants.BEARER.concat(StringUtils.SPACE).concat(accessToken))
                .accessTokenExpirationDate(jwtAccessTokenProvider.getExpirationDateFromAccessToken(accessToken))
                .build();
    }
}

