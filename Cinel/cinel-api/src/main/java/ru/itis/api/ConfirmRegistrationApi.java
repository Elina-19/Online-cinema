package ru.itis.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/api/v1/confirm")
public interface ConfirmRegistrationApi {

    @GetMapping("/{confirm-code}")
    @ResponseStatus(HttpStatus.OK)
    void confirm(@PathVariable("confirm-code") String confirmCode);

}
