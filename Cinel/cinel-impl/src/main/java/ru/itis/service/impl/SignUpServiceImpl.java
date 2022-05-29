package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.SignUpRequest;
import ru.itis.model.Account;
import ru.itis.model.Role;
import ru.itis.repository.AccountRepository;
import ru.itis.service.SignUpService;
import ru.itis.utils.mapper.AccountMapper;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    //TODO: добавить подтверждение по почте, заменить code
    @Override
    public UUID signUp(SignUpRequest signUpRequest) {
        Account account = accountRepository.save(Account.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .hashPassword(passwordEncoder.encode(
                        signUpRequest.getPassword()))
                .code(UUID.randomUUID().toString())
                .isActive(false)
                .role(Role.USER)
                .confirmed(false)
                .build());

        return account.getId();
    }

}
