package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.SignUpApi;
import ru.itis.dto.request.SignUpRequest;
import ru.itis.service.SignUpService;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class SignUpController implements SignUpApi {

    private final ServletRequest sr;

    private final SignUpService signUpService;

    @Override
    public UUID signUp(SignUpRequest signUpRequest) {
        return signUpService.signUp(signUpRequest, sr.getRemoteHost() + ":" + sr.getServerPort());
    }

}
