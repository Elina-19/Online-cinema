package ru.itis.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.response.page.GenrePage;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RequestMapping("/api/v1/genres")
public interface GenreApi {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    GenrePage getGenres(@RequestParam Integer page,
                        @RequestParam(required = false, name = "page_size") Integer pageSize);

}
