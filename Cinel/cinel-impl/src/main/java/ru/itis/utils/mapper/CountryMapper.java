package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.response.CountryResponse;
import ru.itis.model.Country;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryResponse toResponse(Country country);

    Set<CountryResponse> toResponses(Set<Country> countries);

}
