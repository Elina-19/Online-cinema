package ru.itis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.model.Genre;

import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Optional<Genre> findByName(String name);

}
