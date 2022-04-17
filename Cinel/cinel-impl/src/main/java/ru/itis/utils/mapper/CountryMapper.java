package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.request.CountryRequest;
import ru.itis.dto.response.CountryResponse;
import ru.itis.model.Country;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryResponse toResponse(Country country);

    Set<CountryResponse> toCountryResponses(Set<Country> countries);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    Country toEntity(CountryRequest countryRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    Set<Country> toEntities(Set<CountryRequest> countryRequests);

}
