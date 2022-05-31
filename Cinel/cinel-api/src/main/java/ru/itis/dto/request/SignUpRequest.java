package ru.itis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "SignUp")
public class SignUpRequest {

    @NotBlank(message = "Username can not be empty")
    @Size(min = 1, max = 30, message = "Min length of username is {min}, max length is {max}")
    @Schema(description = "Username", example = "Milana")
    private String username;

    @Email
    @Schema(description = "Email", example = "some_example@gmail.com")
    private String email;

    @NotBlank(message = "Password can not be empty")
    @Size(min = 8, max = 12, message = "Min length of password is {min}, max length is {max}")
    @Schema(description = "Password", example = "password1234")
    private String password;

}
