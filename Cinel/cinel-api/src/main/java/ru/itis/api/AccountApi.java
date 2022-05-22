package ru.itis.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/account")
public interface AccountApi<PRINCIPAL> {

    @PutMapping(value = "/room")
    @ResponseStatus(HttpStatus.OK)
    void joinToRoom(@ApiIgnore PRINCIPAL principal, @RequestParam String roomCode);

    @DeleteMapping(value = "/room")
    @ResponseStatus(HttpStatus.OK)
    void leaveRoom(@ApiIgnore PRINCIPAL principal, @RequestParam UUID roomId);

}
