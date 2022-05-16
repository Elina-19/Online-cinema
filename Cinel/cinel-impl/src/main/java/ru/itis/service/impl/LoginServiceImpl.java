package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.LoginRequest;
import ru.itis.dto.response.AccountResponse;
import ru.itis.dto.response.TokenCoupleResponse;
import ru.itis.exception.CinelUnauthorizedException;
import ru.itis.repository.AccountRepository;
import ru.itis.service.JwtTokenService;
import ru.itis.service.LoginService;
import ru.itis.utils.mapper.AccountMapper;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private final AccountRepository accountRepository;

    private final JwtTokenService jwtTokenService;

    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public TokenCoupleResponse login(LoginRequest loginRequest) {
        AccountResponse accountResponse =  accountRepository.findByEmail(loginRequest.getEmail())
                .filter(user -> passwordEncoder.matches(loginRequest.getPassword(), user.getHashPassword()))
                .map(accountMapper::toResponse)
                .orElseThrow(() -> new CinelUnauthorizedException("Failed to log in: " + loginRequest.getEmail()));

        return jwtTokenService.generateTokenCouple(accountResponse);
    }

}
