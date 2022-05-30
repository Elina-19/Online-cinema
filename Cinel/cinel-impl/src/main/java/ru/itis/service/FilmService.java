package ru.itis.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.request.FilmRequest;
import ru.itis.dto.request.FilterSearchRequest;
import ru.itis.dto.response.FilmResponse;
import ru.itis.dto.response.page.FilmPage;
import ru.itis.model.Film;

import java.util.UUID;

public interface FilmService {

    FilmPage getFilms(FilterSearchRequest filmRequest, Integer page, Integer pageSize);

    Film getById(UUID id);

    FilmResponse addFilm(FilmRequest film, MultipartFile file);

}
