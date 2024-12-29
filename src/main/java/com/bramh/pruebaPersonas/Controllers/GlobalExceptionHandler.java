package com.bramh.pruebaPersonas.Controllers;

import com.bramh.pruebaPersonas.Utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> "Campo '" + error.getField() + "' " + error.getDefaultMessage())
                .toList();

        ApiResponse response = new ApiResponse("Error de validación", HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST, LocalDateTime.now(), errors);

        return ResponseEntity.badRequest().body(response);
    }
}
