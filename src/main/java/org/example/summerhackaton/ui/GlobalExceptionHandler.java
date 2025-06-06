package org.example.summerhackaton.ui;

import org.example.summerhackaton.domain.model.exceptions.BadRequestException;
import org.example.summerhackaton.domain.model.exceptions.DatabaseException;
import org.example.summerhackaton.domain.model.exceptions.NoContentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, String> handleAllExceptions(Exception e) {
        return Map.of("error", "Error interno: " + e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ResponseBody
    public Map<String, String> handleBadRequest(BadRequestException e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public ResponseEntity<Void> handleNoContentException(NoContentException e) {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ResponseBody
    public Map<String, String> handleDataBaseException(DatabaseException e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ResponseBody
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return errors;
    }
}

