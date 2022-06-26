package ru.itis.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Film extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    private Integer year;

    @OneToOne
    @JoinColumn (name = "file_info_id", referencedColumnName = "id")
    private FileInfo fileInfo;

    @ManyToMany
    @JoinTable(name = "film_country",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"))
    private Set<Country> countries;

    @ManyToMany
    @JoinTable(name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private Set<Genre> genres;
}
