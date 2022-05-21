package ru.itis.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.RoomRequest;
import ru.itis.dto.response.RoomExtendedResponse;
import ru.itis.dto.response.RoomResponse;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/rooms")
public interface RoomApi {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    RoomResponse createRoom(@RequestBody RoomRequest room);

    @GetMapping(value = "/{room-id}", produces = APPLICATION_JSON_VALUE)
    RoomExtendedResponse getRoom(@PathVariable("room-id") UUID roomId);

    @DeleteMapping("/{room-id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteRoom(@PathVariable("room-id") UUID roomId);

    @PutMapping(value = "/{room-id}/film", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    RoomExtendedResponse changeFilm(@PathVariable("room-id") UUID roomId, @RequestParam UUID filmId);
}
