package ru.itis.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.itis.model.Country;


public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {
}
