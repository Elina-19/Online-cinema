package ru.itis.provider;

import io.jsonwebtoken.Claims;
import ru.itis.dto.enums.Role;
import ru.itis.dto.response.AccountResponse;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface JwtAccessTokenProvider {

    String generateAccessToken(String subject, Map<String, Object> data);

    boolean validateAccessToken(String accessToken, String subject);

    AccountResponse userInfoByToken(String token);

    Claims parseAccessToken(String accessToken);

    String getRoleFromAccessToken(String accessToken);

    Date getExpirationDateFromAccessToken(String accessToken);

    String getSubjectFromAccessToken(String accessToken);

}

