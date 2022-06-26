package ru.itis.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/files")
public interface VideoTranslationApi {

    @GetMapping(value = "/{file-name}")
    @ResponseStatus(HttpStatus.OK)
    Mono<ResponseEntity<byte[]>> getFile(@PathVariable("file-name") String fileName);
}
