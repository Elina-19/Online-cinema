package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Film;

import java.util.UUID;

public interface FilmsRepository extends JpaRepository<Film, UUID> {
}
