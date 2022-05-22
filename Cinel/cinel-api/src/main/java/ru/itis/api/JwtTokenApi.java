package ru.itis.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.TokenCoupleDto;
import ru.itis.dto.response.AccountResponse;
import ru.itis.dto.response.TokenCoupleResponse;

@RequestMapping("/api/v1/token")
public interface JwtTokenApi {

    @GetMapping(value = "/user-info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    AccountResponse userInfoByToken(@RequestParam String token);

    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TokenCoupleResponse updateTokens(@RequestBody TokenCoupleDto tokenCoupleDto);
}
