package ru.itis.controller.handlers;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.itis.exception.CinelServiceException;
import ru.itis.validation.http.ValidationErrorDto;
import ru.itis.validation.http.ValidationExceptionResponse;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionMessage> onAllExceptions(Exception exception) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionMessage.builder()
                        .message(exception.getMessage())
                        .exceptionName(exception.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionMessage> onAccessDeniedException(AccessDeniedException exception) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ExceptionMessage.builder()
                        .message(exception.getMessage())
                        .exceptionName(exception.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(CinelServiceException.class)
    public final ResponseEntity<ExceptionMessage> onAccountExceptionExceptions(CinelServiceException cinelServiceException) {

        return ResponseEntity.status(cinelServiceException.getHttpStatus())
                .body(ExceptionMessage.builder()
                        .message(cinelServiceException.getMessage())
                        .exceptionName(cinelServiceException.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ExceptionMessage> onAuthenticationExceptions(AuthenticationException authenticationException) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionMessage.builder()
                        .message(authenticationException.getMessage())
                        .exceptionName(authenticationException.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(JwtException.class)
    public final ResponseEntity<ExceptionMessage> onJwtExceptions(JwtException jwtException) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionMessage.builder()
                        .message(jwtException.getMessage())
                        .exceptionName(jwtException.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ValidationExceptionResponse> handleException(BindException exception) {

        List<ValidationErrorDto> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {

            String errorMessage = error.getDefaultMessage();
            ValidationErrorDto errorDto = ValidationErrorDto.builder()
                    .message(errorMessage)
                    .field(((FieldError) error).getField())
                    .build();

            errors.add(errorDto);
        });

        return new ResponseEntity<>(ValidationExceptionResponse.builder()
                .errors(errors)
                .build(), HttpStatus.BAD_REQUEST);
    }
}

