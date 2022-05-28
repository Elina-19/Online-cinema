package ru.itis.provider.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.dto.response.AccountResponse;
import ru.itis.exception.token.AuthenticationHeaderException;
import ru.itis.exception.token.IrrelevantTokenException;
import ru.itis.model.Account;
import ru.itis.provider.JwtAccessTokenProvider;
import ru.itis.service.AccountService;
import ru.itis.service.BlackListService;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static ru.itis.consts.CinelConstants.ROLE;

@RequiredArgsConstructor
@Component
public class JwtAccessTokenProviderImpl implements JwtAccessTokenProvider {

    private final AccountService accountService;

    @Value("${jwt.expiration.access.mills}")
    private long expirationAccessInMills;

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final BlackListService blackListService;

    @Override
    public String generateAccessToken(String subject, Map<String, Object> data) {
        Map<String, Object> claims = new HashMap<>(data);
        claims.put(Claims.SUBJECT, subject);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plusMillis(expirationAccessInMills)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    @Override
    public boolean validateAccessToken(String accessToken) {
        try {
            Claims claims = parseAccessToken(accessToken);
            Date date = claims.getExpiration();

            return  date.after(new Date()) &&
                    !blackListService.exists(accessToken);
        } catch (ExpiredJwtException e) {
            throw new AuthenticationHeaderException("Token expired date error");
        }

    }

    @Override
    public AccountResponse userInfoByToken(String token) {
        if (blackListService.exists(token)){
            throw new IrrelevantTokenException(token, "Token is irrelevant");
        }

        try {
            Claims claims = parseAccessToken(token);
            String subject = claims.getSubject();

            return accountService.findByEmail(subject)
                    .orElseThrow(() -> new AuthenticationHeaderException("User with this name was not found"));
        } catch (ExpiredJwtException e) {
            throw new AuthenticationHeaderException("Token expired date error");
        }
    }

    @Override
    public Account userByToken(String token) {
        Claims claims = parseAccessToken(token);

        return accountService.getAccountByEmail(claims.getSubject());
    }

    @Override
    public Claims parseAccessToken(String accessToken) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(accessToken).getBody();
    }

    @Override
    public String getRoleFromAccessToken(String accessToken) {
        try {
            return (String) Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(accessToken).getBody().get(ROLE);
        } catch (ExpiredJwtException e) {
            return (String) e.getClaims().get(ROLE);
        }
    }

    @Override
    public Date getExpirationDateFromAccessToken(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(accessToken)
                    .getBody().getExpiration();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getExpiration();
        }
    }

    @Override
    public String getSubjectFromAccessToken(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(accessToken).getBody().getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }
}
