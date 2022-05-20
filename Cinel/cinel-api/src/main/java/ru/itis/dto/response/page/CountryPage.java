package ru.itis.dto.response.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.dto.response.CountryResponse;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryPage {

    private Set<CountryResponse> countries;

    private Integer totalPages;

}
