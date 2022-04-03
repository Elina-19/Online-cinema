package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Film;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FilmRepository extends JpaRepository<Film, UUID> {

    Optional<List<Film>> findByNameContainingIgnoreCase(String name);

}
