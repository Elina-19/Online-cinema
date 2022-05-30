package ru.itis.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.TokenCoupleDto;
import ru.itis.dto.response.AccountResponse;
import ru.itis.dto.response.TokenCoupleResponse;

@RequestMapping("/api/v1/token")
public interface JwtTokenApi {

    @Operation(summary = "Getting information about user by token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User' information",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = AccountResponse.class)
                            )
                    }
            )
    })
    @GetMapping(value = "/user-info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    AccountResponse userInfoByToken(@Parameter(description = "User identification string")
                                    @RequestParam String token);

    @Operation(summary = "Updating the previously issued access token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New pair of access-refresh tokens",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = TokenCoupleResponse.class)
                            )
                    }
            )
    })
    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TokenCoupleResponse updateTokens(@RequestBody TokenCoupleDto tokenCoupleDto);
}
