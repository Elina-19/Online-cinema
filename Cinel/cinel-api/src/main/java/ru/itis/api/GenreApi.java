package ru.itis.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.response.page.GenrePage;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RequestMapping("/api/v1/genres")
public interface GenreApi {

    @Operation(summary = "Getting genres with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page with genres",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = GenrePage.class)
                            )
                    }
            )
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    GenrePage getGenres(@Parameter(description = "Page index") @RequestParam Integer page,
                        @Parameter(description = "Number of genres on 1 page")
                        @RequestParam(required = false, name = "page_size") Integer pageSize);

}
