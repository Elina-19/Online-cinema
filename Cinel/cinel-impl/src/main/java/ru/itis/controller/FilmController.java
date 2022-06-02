package ru.itis.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.FilmApi;
import ru.itis.dto.request.FilmRequest;
import ru.itis.dto.request.FilterSearchRequest;
import ru.itis.dto.response.FilmResponse;
import ru.itis.dto.response.page.FilmPage;
import ru.itis.service.FilmService;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class FilmController implements FilmApi {

    private final FilmService filmService;

    @Override
    public FilmResponse getFilmById(UUID filmId) {
        return filmService.getFilmResponseById(filmId);
    }

    @Override
    public FilmResponse updateById(UUID filmId, @Valid FilmRequest filmRequest) {
        return filmService.updateById(filmId, filmRequest);
    }

    @Override
    public void deleteById(UUID filmId) {
        filmService.deleteById(filmId);
    }

    @Override
    public FilmPage getFilms(FilterSearchRequest request, Integer page, Integer pageSize) {
        return filmService.getFilms(request, page, pageSize);
    }

    @Override
    public FilmResponse addFilm(FilmRequest film) {
        return filmService.addFilm(film);
    }
}
