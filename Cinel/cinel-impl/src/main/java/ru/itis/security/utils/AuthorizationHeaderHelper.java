package ru.itis.security.utils;

import ru.itis.exception.token.AuthenticationHeaderException;
import ru.itis.utils.HttpRequestUtil;

public class AuthorizationHeaderHelper {

    public static String getTokenFromValidatedAuthorizationHeader(String authorizationHeader) {

        if (authorizationHeader == null) {
            return null;
        }

        if (!authorizationHeader.startsWith("Bearer")) {
            throw new AuthenticationHeaderException("Invalid authentication scheme found in Authorization header");
        }

        String token = HttpRequestUtil.getTokenFromAuthorizationHeader(authorizationHeader);
        if (token == null) {
            throw new AuthenticationHeaderException("Authorization header token is empty");
        }

        return token;
    }

}
