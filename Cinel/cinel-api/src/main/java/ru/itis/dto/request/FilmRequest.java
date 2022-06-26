package ru.itis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Film")
public class FilmRequest {

    @NotBlank
    @Size(min = 1, max = 100, message = "Min length of name is {min}, max length is {max}")
    @Schema(description = "Name", example = "Pirates of the Caribbean")
    private String name;

    @NotBlank
    @Size(min = 1, max = 255, message = "Min length of description is {min}, max length is {max}")
    @Schema(description = "Description", example = "Pirates of the Caribbean is a Disney media franchise" +
            " encompassing numerous theme park rides")
    private String description;

    @Schema(description = "Video file")
    private MultipartFile file;

    @Schema(description = "Year of creation", example = "2022")
    private Integer year;

    private Set<UUID> countriesIds;

    private Set<UUID> genresIds;

}
