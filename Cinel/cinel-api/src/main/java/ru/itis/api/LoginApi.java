package ru.itis.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.dto.request.LoginRequest;
import ru.itis.dto.response.TokenCoupleResponse;

@RequestMapping("/api/v1/login")
public interface LoginApi {

    @Operation(summary = "Getting tokens based on registered data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pair of access-refresh tokens",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = TokenCoupleResponse.class)
                            )
                    }
            )
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TokenCoupleResponse login(@RequestBody LoginRequest loginRequest);

}
