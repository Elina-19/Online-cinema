package ru.itis.filter;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.itis.dto.request.FilmFilterRequest;
import ru.itis.dto.request.FilterSearchRequest;
import ru.itis.model.*;

import javax.persistence.criteria.JoinType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@UtilityClass
public class FilmFilters {

    public static Specification<Film> fromFilterSearchRequest(FilterSearchRequest filmRequest) {
        List<Specification<Film>> specifications = new ArrayList<>();
        if (filmRequest == null) {
            return null;
        }

        if (filmRequest.getFilter() != null) {
            specifications.add(fromFilter(filmRequest.getFilter()));
        }

        if (filmRequest.getSearch() != null) {
            specifications.add(fromSearch(filmRequest.getSearch()));
        }

        return specifications.stream()
                .reduce(Specification::and)
                .orElse(null);
    }

    public static Specification<Film> fromSearch(String search) {

        List<Specification<Film>> specifications = new ArrayList<>();
        specifications.add(Specifications.like(Film_.NAME, search));
        specifications.add(Specifications.like(Film_.DESCRIPTION, search));

        return specifications.stream()
                .reduce(Specification::or)
                .orElse(null);
    }

    public static Specification<Film> fromFilter(FilmFilterRequest filter) {

        List<Specification<Film>> specifications = new ArrayList<>();

        if (filter.getYear() != null) {
            specifications.add(Specifications.equal(Film_.YEAR, filter.getYear()));
        }

        List<UUID> genresUuids = filter.getGenres().stream()
                .map(request -> UUID.fromString(request.getId()))
                .collect(Collectors.toList());

        if (filter.getGenres() != null) {
            specifications.add(
                    Specifications.in(JoinType.INNER, Film_.GENRES + "." + Genre_.ID, genresUuids)
            );
        }

        List<UUID> countriesUuids = filter.getCountries().stream()
                .map(request -> UUID.fromString(request.getId()))
                .collect(Collectors.toList());

        if (filter.getGenres() != null) {
            specifications.add(
                    Specifications.in(JoinType.INNER, Film_.COUNTRIES + "." + Country_.ID, countriesUuids)
            );
        }

        return specifications.stream()
                .reduce(Specification::and)
                .orElse(null);
    }

}
