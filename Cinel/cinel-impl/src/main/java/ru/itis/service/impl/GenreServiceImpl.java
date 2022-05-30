package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.dto.response.page.GenrePage;
import ru.itis.model.Genre;
import ru.itis.repository.GenreRepository;
import ru.itis.service.GenreService;
import ru.itis.utils.mapper.GenreMapper;

import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Value("${cinel.default-page-size}")
    private int defaultPageSize;


    @Override
    public GenrePage getGenres(Integer page, Integer pageSize) {

        if (pageSize == null) {
            pageSize = defaultPageSize;
        }

        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Genre> genrePage = genreRepository.findAll(pageRequest);

        return GenrePage.builder()
                .genres(genreMapper.toResponses(new HashSet<>(genrePage.getContent())))
                .totalPages(genrePage.getTotalPages())
                .build();
    }
}
