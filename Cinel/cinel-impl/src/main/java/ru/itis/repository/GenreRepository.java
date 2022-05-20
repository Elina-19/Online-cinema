package ru.itis.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.itis.model.Genre;


public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {
}
