package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.request.GenreRequest;
import ru.itis.dto.response.GenreResponse;
import ru.itis.model.Genre;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    @Mapping(target = "id", expression = "java(genre.getId().toString())")
    GenreResponse toResponse(Genre genre);

    Set<GenreResponse> toResponses(Set<Genre> genres);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    Genre toEntity(GenreRequest genreRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    Set<Genre> toEntities(Set<GenreRequest> genreRequests);

}
