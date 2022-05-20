package ru.itis.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.GenreApi;
import ru.itis.dto.response.page.GenrePage;
import ru.itis.service.GenreService;

@RequiredArgsConstructor
@RestController
public class GenreController implements GenreApi {

    private final GenreService genreService;

    @Override
    public GenrePage getGenres(Integer page) {
        return genreService.getGenres(page);
    }
}
