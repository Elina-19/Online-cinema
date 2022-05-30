package ru.itis.api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.response.RoomExtendedResponse;
import ru.itis.dto.response.RoomResponse;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/rooms")
public interface RoomApi<PRINCIPAL> {

    @Operation(summary = "Creating a room by the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Data of new room",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = RoomResponse.class)
                            )
                    }
            )
    })
    @ApiImplicitParam(name = "Authorization", paramType = "header")
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    RoomResponse createRoom(@ApiIgnore PRINCIPAL principal);

    @Operation(summary = "Getting room by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data of room",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = RoomExtendedResponse.class)
                            )
                    }
            )
    })
    @GetMapping(value = "/{room-id}", produces = APPLICATION_JSON_VALUE)
    RoomExtendedResponse getRoom(@PathVariable("room-id") UUID roomId);

    @Operation(summary = "Deleting room by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful deletion"
            )
    })
    @ApiImplicitParam(name = "Authorization", paramType = "header")
    @DeleteMapping("/{room-id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteRoom(@ApiIgnore PRINCIPAL principal, @PathVariable("room-id") UUID roomId);

    @Operation(summary = "Changing the movie by the creator of the room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data of room",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = RoomExtendedResponse.class)
                            )
                    }
            )
    })
    @ApiImplicitParam(name = "Authorization", paramType = "header")
    @PutMapping(value = "/{room-id}/film", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    RoomExtendedResponse changeFilm(@ApiIgnore PRINCIPAL principal, @PathVariable("room-id") UUID roomId,
                                    @RequestParam UUID filmId);
}
