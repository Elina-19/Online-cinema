package ru.itis.service;

import ru.itis.dto.request.CountryRequest;
import ru.itis.model.Country;

import java.util.Set;

public interface CountryService {

    Set<Country> getCountriesByRequest(Set<CountryRequest> countryRequests);
}
