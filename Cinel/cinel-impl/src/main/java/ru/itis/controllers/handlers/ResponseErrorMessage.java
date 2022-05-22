package ru.itis.controllers.handlers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
public class ResponseErrorMessage {

    private final long timestamp = Instant.now().getEpochSecond();
    private final List<Error> errors;

    @AllArgsConstructor
    @Getter
    public static class Error {
        private final String fieldName;
        private final String code;
        private final String detailMessage;
    }

}

