package com.rayyanhunerkar.todolist.exception;

import com.rayyanhunerkar.todolist.model.Error;
import io.jsonwebtoken.ExpiredJwtException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleAllException(Exception ex, WebRequest request) throws Exception {
        Error errorDetails = new Error(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<Error>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<Error> handleUserNotFoundAllException(Exception ex, WebRequest request) throws Exception {
        Error errorDetails = new Error(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<Error>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistsException.class)
    public final ResponseEntity<Error> handleUserExistsException(Exception ex, WebRequest request) throws Exception {
        Error errorDetails = new Error(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({JwtException.class})
    public final ResponseEntity<Error> handleJwtException(Exception ex, WebRequest request) throws Exception {
        Error errorDetails = new Error(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @Override()
    public final ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request
    ) {
        String errors = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ex.getFieldErrors().size(); i++) {
            errors = String.valueOf(sb.append("Error: ").append(ex.getFieldErrors().get(i).getDefaultMessage()).append(" \n "));
        }
        Error errorDetails = new Error(
                LocalDateTime.now(),
                errors,
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}

