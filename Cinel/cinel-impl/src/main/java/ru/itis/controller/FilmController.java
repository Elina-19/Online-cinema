package ru.itis.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.api.FilmApi;
import ru.itis.dto.request.FilmRequest;
import ru.itis.dto.request.FilterSearchRequest;
import ru.itis.dto.response.FilmResponse;
import ru.itis.dto.response.page.FilmPage;
import ru.itis.service.FilmService;

@RequiredArgsConstructor
@RestController
public class FilmController implements FilmApi {

    private final FilmService filmService;

    @Override
    public FilmPage getFilms(FilterSearchRequest request, Integer page) {
        return filmService.getFilms(request, page);
    }

    @Override
    public FilmResponse addFilm(FilmRequest film) {
        return filmService.addFilm(film);
    }
}
