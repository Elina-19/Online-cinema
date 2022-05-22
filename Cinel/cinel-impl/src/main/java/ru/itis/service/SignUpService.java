package ru.itis.service;

import ru.itis.dto.request.SignUpRequest;

import java.util.UUID;

public interface SignUpService {

    UUID signUp(SignUpRequest signUpRequest);

}
