package ru.itis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.model.Country;

import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country, Long> {

    Optional<Country> findByName(String name);

}
