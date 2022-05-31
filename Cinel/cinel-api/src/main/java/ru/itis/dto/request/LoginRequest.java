package ru.itis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Login")
public class LoginRequest {

    @Schema(description = "Email", example = "some_example@gmail.com")
    private String email;

    @Schema(description = "Password", example = "password1234")
    private String password;

}
