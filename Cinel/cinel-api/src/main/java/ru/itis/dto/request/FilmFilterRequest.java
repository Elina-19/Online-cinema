package ru.itis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmFilterRequest {

    private Integer year;

    private Set<CountryRequest> countries;

    private Set<GenreRequest> genres;

}
