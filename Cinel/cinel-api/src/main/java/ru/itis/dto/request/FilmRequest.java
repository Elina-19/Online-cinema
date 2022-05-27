package ru.itis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmRequest {

    private String name;

    private String description;

    private Integer year;

    private Set<UUID> countriesIds;

    private Set<UUID> genresIds;

}
