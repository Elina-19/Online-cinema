package ru.itis.service;

import ru.itis.dto.request.FilterSearchRequest;
import ru.itis.dto.response.FilmPage;

public interface FilmService {

    FilmPage getFilms(FilterSearchRequest filmRequest, Integer page);

}
