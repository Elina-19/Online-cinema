package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Film extends AbstractEntity{
    @Column(nullable = false)
    private String name;

    private String description;
    private Integer year;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"))
    private List<Country> countries;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"),
    @JoinColumn(name = "film_id", referencedColumnName = "id"))
    private List<Genre> genres;
}
