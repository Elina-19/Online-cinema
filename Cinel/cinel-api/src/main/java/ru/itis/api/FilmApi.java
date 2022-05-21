package ru.itis.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.FilterSearchRequest;
import ru.itis.dto.response.page.FilmPage;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RequestMapping("/api/v1/films")
public interface FilmApi {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    FilmPage getFilms(@RequestBody FilterSearchRequest filmRequest, @RequestParam Integer page);

}
