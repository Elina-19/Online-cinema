package ru.itis.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.FilmRequest;
import ru.itis.dto.request.FilterSearchRequest;
import ru.itis.dto.response.FilmResponse;
import ru.itis.dto.response.page.FilmPage;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/films")
public interface FilmApi {

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    FilmPage getFilms(@RequestBody FilterSearchRequest filmRequest, @RequestParam Integer page);

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    FilmResponse addFilm(FilmRequest film);

}
