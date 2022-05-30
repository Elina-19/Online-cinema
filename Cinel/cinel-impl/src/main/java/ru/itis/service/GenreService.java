package ru.itis.service;

import ru.itis.dto.response.page.GenrePage;


public interface GenreService {

    GenrePage getGenres(Integer page, Integer pageSize);

}
