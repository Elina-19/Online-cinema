package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import ru.itis.dto.response.GenreResponse;
import ru.itis.model.Genre;

import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreResponse toResponse(Genre genre);

    Set<GenreResponse> toResponses(Set<Genre> genres);

    Genre toEntity(UUID id);

    Set<Genre> toEntities(Set<UUID> ids);

}
