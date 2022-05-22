package ru.itis.dto.response.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.dto.response.GenreResponse;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenrePage {

    private Set<GenreResponse> genres;

    private Integer totalPages;

}
