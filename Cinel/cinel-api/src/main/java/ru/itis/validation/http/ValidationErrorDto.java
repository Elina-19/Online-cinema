package ru.itis.validation.http;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationErrorDto {

    @Schema(example = "username")
    private String field;

    @Schema(example = "Username can not be empty")
    private String message;
}

