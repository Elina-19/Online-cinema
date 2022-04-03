package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import ru.itis.dto.response.GenreResponse;
import ru.itis.model.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreResponse toResponse(Genre genre);

}
