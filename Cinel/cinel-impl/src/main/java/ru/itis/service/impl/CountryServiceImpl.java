package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.dto.response.page.CountryPage;
import ru.itis.model.Country;
import ru.itis.repository.CountryRepository;
import ru.itis.service.CountryService;
import ru.itis.utils.PageSizeUtil;
import ru.itis.utils.mapper.CountryMapper;

import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    @Value("${cinel.default-page-size}")
    private int defaultPageSize;


    @Override
    public CountryPage getCountries(Integer page, Integer pageSize) {

        pageSize = PageSizeUtil.processPageSize(pageSize, defaultPageSize);
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Country> countryPage = countryRepository.findAll(pageRequest);

        return CountryPage.builder()
                .countries(countryMapper.toResponses(new HashSet<>(countryPage.getContent())))
                .totalPages(countryPage.getTotalPages())
                .build();
    }
}
