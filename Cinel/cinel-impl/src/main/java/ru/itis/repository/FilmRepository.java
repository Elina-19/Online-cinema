package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.itis.model.Film;

import java.util.UUID;

public interface FilmRepository extends JpaRepository<Film, UUID>, JpaSpecificationExecutor<Film> {

}
