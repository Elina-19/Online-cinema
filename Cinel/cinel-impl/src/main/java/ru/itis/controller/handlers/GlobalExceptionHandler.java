package ru.itis.controller.handlers;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.itis.exception.CinelServiceException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<ExceptionMessage> onAllExceptions(Exception exception) {
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(ExceptionMessage.builder()
//                        .message(exception.getMessage())
//                        .exceptionName(exception.getClass().getSimpleName())
//                        .build());
//    }

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

}

