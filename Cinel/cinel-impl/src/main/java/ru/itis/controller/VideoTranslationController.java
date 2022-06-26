package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import ru.itis.api.VideoTranslationApi;
import ru.itis.service.FileService;

@RequiredArgsConstructor
@Controller
public class VideoTranslationController implements VideoTranslationApi {

    private final FileService fileService;

    @Override
    public Mono<ResponseEntity<byte[]>> getFile(String fileName) {
        return Mono.just(fileService.getFile(fileName));
    }
}

