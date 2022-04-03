package ru.itis.service;

import ru.itis.dto.response.FilmResponse;

import java.util.List;

public interface FilmService {

    List<FilmResponse> getFilmsByName(String name);

}
