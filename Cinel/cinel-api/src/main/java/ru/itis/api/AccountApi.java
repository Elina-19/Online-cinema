package ru.itis.api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

@RequestMapping("/api/v1/account")
public interface AccountApi<PRINCIPAL> {

    @Operation(summary = "Joining a movie viewing room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully joining an existing room with a valid code"
            )
    })
    @ApiImplicitParam(name = "Authorization", paramType = "header")
    @PutMapping(value = "/room")
    @ResponseStatus(HttpStatus.OK)
    void joinToRoom(@ApiIgnore PRINCIPAL principal,
                    @Parameter(description = "Code for joining a specific room", example = "iaZUH9Adzh")
                    @RequestParam String roomCode);

    @Operation(summary = "Leaving a movie viewing room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully leaving room with an existing id"
            )
    })
    @ApiImplicitParam(name = "Authorization", paramType = "header")
    @DeleteMapping(value = "/room")
    @ResponseStatus(HttpStatus.OK)
    void leaveRoom(@ApiIgnore PRINCIPAL principal,
                   @Parameter(description = "Id of the room the user wants to leave",
                           example = "123e4567-e89b-12d3-a456-556642440000")
                   @RequestParam UUID roomId);

}
