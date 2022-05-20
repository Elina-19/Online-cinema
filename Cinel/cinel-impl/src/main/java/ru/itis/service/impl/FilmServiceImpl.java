package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.FilmFilter;
import ru.itis.dto.request.FilterSearchRequest;
import ru.itis.dto.request.FilmSearch;
import ru.itis.dto.response.FilmPage;
import ru.itis.model.Country;
import ru.itis.model.Film;
import ru.itis.model.Genre;
import ru.itis.repository.FilmRepository;
import ru.itis.repository.specification.FilmSpecification;
import ru.itis.repository.specification.Film_;
import ru.itis.repository.specification.SearchCriteria;
import ru.itis.repository.specification.SearchOperation;
import ru.itis.service.CountryService;
import ru.itis.service.FilmService;
import ru.itis.service.GenreService;
import ru.itis.utils.mapper.FilmMapper;

import java.util.Set;


@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    private final CountryService countryService;

    private final GenreService genreService;

    private final FilmMapper filmMapper;


    @Value("${cinel.default-page-size}")
    private int defaultPageSize;


    @Override
    public FilmPage getFilms(FilterSearchRequest filmRequest, Integer page) {
        FilmSpecification specification = new FilmSpecification();

        FilmSearch search = filmRequest.getSearch();
        FilmFilter filter = filmRequest.getFilter();

        if (filter != null) {
            addFilterCriteria(specification, filter);
        }

        if (search != null) {
            addSearchCriteria(specification, search);
        }

        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Film> filmPage = filmRepository.findAll(specification, pageRequest);

        return FilmPage.builder()
                .films(filmMapper.toFilmResponses(filmPage.getContent()))
                .totalPages(filmPage.getTotalPages())
                .build();

    }

    private void addSearchCriteria(FilmSpecification specification, FilmSearch search) {
        if (search.getName() != null) {
            specification.add(
                    new SearchCriteria(Film_.NAME, search.getName(), SearchOperation.LIKE)
            );
        }

        if (search.getDescription() != null) {
            specification.add(
                    new SearchCriteria(Film_.DESCRIPTION, search.getDescription(), SearchOperation.LIKE)
            );
        }
    }

    private void addFilterCriteria(FilmSpecification specification, FilmFilter filter) {
        if (filter.getYear() != null) {
            specification.add(
                    new SearchCriteria(Film_.YEAR, filter.getYear(), SearchOperation.EQUAL)
            );
        }

        if (filter.getCountries() != null) {
            Set<Country> countries = countryService.getCountriesByRequest(filter.getCountries());
            for (Country country : countries) {
                specification.add(
                        new SearchCriteria(Film_.COUNTRIES, country, SearchOperation.IN)
                );
            }

        }

        if (filter.getGenres() != null) {
            Set<Genre> genres = genreService.getGenresByRequest(filter.getGenres());
            for (Genre genre : genres) {
                specification.add(
                        new SearchCriteria(Film_.GENRES, genre, SearchOperation.IN)
                );
            }
        }

    }
}
