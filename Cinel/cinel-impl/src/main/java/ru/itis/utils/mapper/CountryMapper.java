package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import ru.itis.dto.response.CountryResponse;
import ru.itis.model.Country;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryResponse toResponse(Country country);

}
