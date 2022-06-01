package ru.itis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmRequest {

    @Schema(description = "Name", example = "Pirates of the Caribbean")
    private String name;

    @Schema(description = "Description", example = "Pirates of the Caribbean is a Disney media franchise" +
            " encompassing numerous theme park rides, a series of films")
    private String description;

    @Schema(description = "Video file")
    private MultipartFile file;

    @Schema(description = "Year of creation", example = "2022")
    private Integer year;

    private Set<UUID> countriesIds;

    private Set<UUID> genresIds;

}
