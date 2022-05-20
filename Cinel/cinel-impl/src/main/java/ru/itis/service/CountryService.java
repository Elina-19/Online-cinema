package ru.itis.service;

import ru.itis.dto.response.page.CountryPage;


public interface CountryService {

    CountryPage getCountries(Integer page);

}
