package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import ru.itis.dto.response.CountryResponse;
import ru.itis.model.Country;

import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryResponse toResponse(Country country);

    Set<CountryResponse> toResponses(Set<Country> countries);

    Country toEntity(UUID id);

    Set<Country> toEntities(Set<UUID> ids);
}
