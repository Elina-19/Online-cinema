package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.request.FilmRequest;
import ru.itis.dto.response.FilmResponse;
import ru.itis.model.Film;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountryMapper.class, GenreMapper.class})
public interface FilmMapper {

    FilmResponse toResponse(Film film);

    List<FilmResponse> toResponses(List<Film> films);

    @Mapping(target = "countries", source = "countriesIds")
    @Mapping(target = "genres", source = "genresIds")
    @Mapping(target = "fileInfo", ignore = true)
    @Mapping(target = "id", ignore = true)
    Film toEntity(FilmRequest film);

}
