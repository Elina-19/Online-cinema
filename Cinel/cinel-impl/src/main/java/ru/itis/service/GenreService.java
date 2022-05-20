package ru.itis.service;

import ru.itis.dto.request.GenreRequest;
import ru.itis.model.Genre;

import java.util.Set;

public interface GenreService {

    Set<Genre> getGenresByRequest(Set<GenreRequest> genreRequests);
}
