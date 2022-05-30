package ru.itis.service;

import ru.itis.dto.request.FilterSearchRequest;
import ru.itis.dto.response.page.FilmPage;
import ru.itis.model.Film;

import java.util.UUID;

public interface FilmService {

    FilmPage getFilms(FilterSearchRequest filmRequest, Integer page, Integer pageSize);

    Film getById(UUID id);

}
