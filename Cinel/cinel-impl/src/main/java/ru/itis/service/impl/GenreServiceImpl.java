package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.GenreRequest;
import ru.itis.exception.GenreNotFoundException;
import ru.itis.model.Genre;
import ru.itis.repository.GenreRepository;
import ru.itis.service.GenreService;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Set<Genre> getGenresByRequest(Set<GenreRequest> genreRequests) {
        Set<Genre> genres = new HashSet<>();
        for (GenreRequest genreRequest : genreRequests) {
            genres.add(
                    genreRepository.findByName(genreRequest.getName())
                            .orElseThrow(GenreNotFoundException::new)
            );
        }
        return genres;
    }
}
