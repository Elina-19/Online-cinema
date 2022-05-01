package ru.itis.service;

import ru.itis.model.Film;

import java.util.UUID;

public interface FilmService {

    Film getById(UUID id);

}
