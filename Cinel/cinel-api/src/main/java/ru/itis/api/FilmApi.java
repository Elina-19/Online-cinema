package ru.itis.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.request.FilmRequest;
import ru.itis.dto.request.FilterSearchRequest;
import ru.itis.dto.response.FilmResponse;
import ru.itis.dto.response.page.FilmPage;

import javax.validation.Valid;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RequestMapping("/api/v1/films")
public interface FilmApi {

    @Operation(summary = "Getting films with pagination by filter and search")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page with films",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = FilterSearchRequest.class)
                            )
                    }
            )
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    FilmPage getFilms(@RequestBody FilterSearchRequest filmRequest,
                      @Parameter(description = "Page index") @RequestParam Integer page,
                      @Parameter(description = "Number of films on 1 page")
                      @RequestParam(required = false, name = "page_size") Integer pageSize);



    @PutMapping(consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    FilmResponse addFilm(@RequestPart FilmRequest film, @RequestPart MultipartFile file);

}
