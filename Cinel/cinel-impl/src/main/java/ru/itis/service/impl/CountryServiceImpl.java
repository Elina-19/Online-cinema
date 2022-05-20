package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.CountryRequest;
import ru.itis.exception.CountryNotFoundException;
import ru.itis.model.Country;
import ru.itis.repository.CountryRepository;
import ru.itis.service.CountryService;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Set<Country> getCountriesByRequest(Set<CountryRequest> countryRequests) {
        Set<Country> countries = new HashSet<>();
        for (CountryRequest countryRequest : countryRequests) {
            countries.add(
                    countryRepository.findByName(countryRequest.getName())
                            .orElseThrow(CountryNotFoundException::new)
            );
        }
        return countries;
    }
}
