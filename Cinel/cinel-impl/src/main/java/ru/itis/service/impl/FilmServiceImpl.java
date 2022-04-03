package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.response.FilmResponse;
import ru.itis.exception.FilmNotFoundException;
import ru.itis.model.Film;
import ru.itis.repository.FilmRepository;
import ru.itis.service.FilmService;
import ru.itis.utils.mapper.FilmMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    private final FilmMapper filmMapper;

    @Override
    public List<FilmResponse> getFilmsByName(String name) {
        List<Film> films = filmRepository.findByNameContainingIgnoreCase(name).orElseThrow(FilmNotFoundException::new);
        return films.stream().map(filmMapper::toResponse).collect(Collectors.toList());
    }
}
