package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.FilterSearchRequest;
import ru.itis.dto.response.page.FilmPage;
import ru.itis.model.Film;
import ru.itis.repository.FilmRepository;
import ru.itis.filter.FilmFilters;
import ru.itis.service.FilmService;
import ru.itis.utils.mapper.FilmMapper;


@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    private final FilmMapper filmMapper;


    @Value("${cinel.default-page-size}")
    private int defaultPageSize;


    @Override
    public FilmPage getFilms(FilterSearchRequest filmRequest, Integer page) {

        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Film> filmPage = filmRepository.findAll(FilmFilters.fromFilterSearchRequest(filmRequest), pageRequest);

        return FilmPage.builder()
                .films(filmMapper.toResponses(filmPage.getContent()))
                .totalPages(filmPage.getTotalPages())
                .build();
    }
}
