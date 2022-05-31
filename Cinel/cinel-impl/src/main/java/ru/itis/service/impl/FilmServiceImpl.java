package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.request.FilmRequest;
import ru.itis.dto.request.FilterSearchRequest;
import ru.itis.dto.response.FilmResponse;
import ru.itis.dto.response.page.FilmPage;
import ru.itis.model.FileInfo;
import ru.itis.model.Film;
import ru.itis.repository.FilmRepository;
import ru.itis.filter.FilmFilters;
import ru.itis.service.FileService;
import ru.itis.service.FilmService;
import ru.itis.utils.PageSizeUtil;
import ru.itis.utils.mapper.FilmMapper;
import ru.itis.exception.FilmNotExistException;

import java.io.OutputStream;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    private final FilmMapper filmMapper;

    private final FileService fileService;


    @Value("${cinel.default-page-size}")
    private int defaultPageSize;


    @Override
    public FilmPage getFilms(FilterSearchRequest filmRequest, Integer page, Integer pageSize) {

        pageSize = PageSizeUtil.processPageSize(pageSize, defaultPageSize);
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Film> filmPage = filmRepository.findAll(FilmFilters.fromFilterSearchRequest(filmRequest), pageRequest);

        return FilmPage.builder()
                .films(filmMapper.toResponses(filmPage.getContent()))
                .totalPages(filmPage.getTotalPages())
                .build();
    }

    @Override
    public Film getById(UUID id) {
        return filmRepository
                .findById(id)
                .orElseThrow(()
                        -> new FilmNotExistException(id));

    }

    @Override
    public FilmResponse addFilm(FilmRequest film, MultipartFile file) {
        FileInfo fileInfo = fileService.upload(file);
        Film newFilm = filmMapper.toEntity(film);
        newFilm.setFileInfo(fileInfo);
        return filmMapper.toResponse(newFilm);
    }
}
