package ru.itis.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/api/v1/confirm")
public interface ConfirmRegistrationApi {

    @Operation(summary = "Confirmation of some user data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "The user's status changes to confirmed"
            )
    })
    @GetMapping("/{confirm-code}")
    @ResponseStatus(HttpStatus.OK)
    void confirm(@Parameter(description = "Personal confirmation code")
                 @PathVariable("confirm-code") String confirmCode);
}
