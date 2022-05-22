package ru.itis.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.response.RoomExtendedResponse;
import ru.itis.dto.response.RoomResponse;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/rooms")
public interface RoomApi<PRINCIPAL> {

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    RoomResponse createRoom(@ApiIgnore PRINCIPAL principal);

    @GetMapping(value = "/{room-id}", produces = APPLICATION_JSON_VALUE)
    RoomExtendedResponse getRoom(@PathVariable("room-id") UUID roomId);

    @DeleteMapping("/{room-id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteRoom(@ApiIgnore PRINCIPAL principal, @PathVariable("room-id") UUID roomId);

    @PutMapping(value = "/{room-id}/film", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    RoomExtendedResponse changeFilm(@ApiIgnore PRINCIPAL principal, @PathVariable("room-id") UUID roomId, @RequestParam UUID filmId);
}
