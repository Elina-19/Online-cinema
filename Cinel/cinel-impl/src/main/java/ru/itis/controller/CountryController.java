package ru.itis.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.api.CountryApi;
import ru.itis.dto.response.page.CountryPage;
import ru.itis.service.CountryService;

@RequiredArgsConstructor
@RestController
public class CountryController implements CountryApi {

    private final CountryService countryService;

    @Override
    public CountryPage getCountries(Integer page) {
        return countryService.getCountries(page);
    }
}
