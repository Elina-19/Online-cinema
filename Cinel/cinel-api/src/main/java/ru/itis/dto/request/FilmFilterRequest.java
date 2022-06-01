package ru.itis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class FilmFilterRequest {

    @Schema(description = "Year of creation", example = "2002")
    private Integer year;

    private Set<UUID> countriesIds;

    private Set<UUID> genresIds;

}
