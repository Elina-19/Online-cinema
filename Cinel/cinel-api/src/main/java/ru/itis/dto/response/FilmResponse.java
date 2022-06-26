package ru.itis.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmResponse {

    private String name;

    private String description;

    private Integer year;

    private List<CountryResponse> countries;

    private List<GenreResponse> genres;
}
