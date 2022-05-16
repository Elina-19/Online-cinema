package ru.itis.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.AccountRequest;
import ru.itis.dto.response.TokenCoupleResponse;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/accounts")
public interface AccountApi {

    @PutMapping(value = "/{account-id}/room",
            consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void joinToRoom(@PathVariable("account-id") UUID accountId, @RequestParam String roomCode);

    @DeleteMapping(value = "/{account-id}/room")
    @ResponseStatus(HttpStatus.OK)
    void leaveRoom(@PathVariable("account-id") UUID accountId, @RequestParam UUID roomId);

}
