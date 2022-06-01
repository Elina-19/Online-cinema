package ru.itis.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.response.page.CountryPage;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RequestMapping("/api/v1/countries")
public interface CountryApi {

    @Operation(summary = "Getting countries with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page with countries",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = CountryPage.class)
                            )
                    }
            )
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CountryPage getCountries(@Parameter(description = "Page index") @RequestParam Integer page,
                             @Parameter(description = "Number of countries on 1 page")
                             @RequestParam(required = false, name = "page_size") Integer pageSize);

}
