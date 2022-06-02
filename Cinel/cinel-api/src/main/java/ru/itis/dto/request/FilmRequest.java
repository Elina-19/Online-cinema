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

    @NotBlank(message = "Name can not be empty")
    @Size(min = 1, max = 50, message = "Min length of film's name is {min}, max length is {max}")
    @Schema(description = "Name", example = "The maze runner")
    private String name;

    @Schema(description = "Description")
    private String description;

    private MultipartFile file;

    private Integer year;

    private Set<UUID> countriesIds;

    private Set<UUID> genresIds;

}
