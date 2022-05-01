package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.exception.FilmNotExistException;
import ru.itis.model.Film;
import ru.itis.repository.FilmRepository;
import ru.itis.service.FilmService;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Override
    public Film getById(UUID id) {
        return filmRepository
                .findById(id)
                .orElseThrow(()
                        -> new FilmNotExistException(id));
    }
}
