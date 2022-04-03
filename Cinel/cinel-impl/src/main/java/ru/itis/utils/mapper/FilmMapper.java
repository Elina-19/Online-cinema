package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import ru.itis.dto.response.FilmResponse;
import ru.itis.model.Film;

@Mapper(componentModel = "spring", uses = {CountryMapper.class, GenreMapper.class})
public interface FilmMapper {

    FilmResponse toResponse(Film film);

}
