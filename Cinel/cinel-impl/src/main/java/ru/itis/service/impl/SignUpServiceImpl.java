package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.SignUpRequest;
import ru.itis.exception.confirm_code.ConfirmCodeExpired;
import ru.itis.exception.confirm_code.IllegalConfirmCodeException;
import ru.itis.model.Account;
import ru.itis.model.Role;
import ru.itis.repository.AccountRepository;
import ru.itis.service.SignUpService;
import ru.itis.utils.EmailUtil;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    @Value("${cinel.confirm-code.seconds}")
    private long expirationConfirmCode;

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailUtil emailUtil;

    @Transactional
    @Override
    public UUID signUp(SignUpRequest signUpRequest, String path) {
        Account account = accountRepository.save(Account.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .hashPassword(passwordEncoder.encode(
                        signUpRequest.getPassword()))
                .code("code")
                .isActive(false)
                .role(Role.USER)
                .confirmed(false)
                .code(UUID.randomUUID().toString())
                .codeSent(LocalDateTime.now())
                .build());

        Map<String, String> map = new HashMap<>();
        map.put("username", account.getUsername());
        map.put("confirmLink", path + "/api/v1/confirm/" + account.getCode());

        emailUtil.sendMail(account.getEmail(), "confirm", "confirm_mail.ftlh", map);

        return account.getId();
    }

    @Transactional
    @Override
    public void confirm(String confirmCode) {
        Optional<Account> optionalAccount = accountRepository.findByCode(confirmCode);

        if (optionalAccount.isPresent()){
            Account account = optionalAccount.get();

            if (!account.getCodeSent().plusSeconds(expirationConfirmCode)
                    .isAfter(LocalDateTime.now())){
                throw new ConfirmCodeExpired();
            }

            account.setConfirmed(true);
            accountRepository.save(account);

        }else {
            throw new IllegalConfirmCodeException();
        }
    }

}
