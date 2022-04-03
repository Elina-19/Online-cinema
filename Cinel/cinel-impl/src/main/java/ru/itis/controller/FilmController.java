package ru.itis.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.FilmApi;
import ru.itis.dto.response.FilmResponse;
import ru.itis.service.FilmService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FilmController implements FilmApi {

    private final FilmService filmService;

    @Override
    public List<FilmResponse> getFilmsByName(String name) {
        return filmService.getFilmsByName(name);
    }
}
