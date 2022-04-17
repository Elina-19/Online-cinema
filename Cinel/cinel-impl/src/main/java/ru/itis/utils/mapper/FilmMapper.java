package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import ru.itis.dto.response.FilmResponse;
import ru.itis.model.Film;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountryMapper.class, GenreMapper.class})
public interface FilmMapper {

    FilmResponse toResponse(Film film);

    List<FilmResponse> toFilmResponses(List<Film> films);

}
