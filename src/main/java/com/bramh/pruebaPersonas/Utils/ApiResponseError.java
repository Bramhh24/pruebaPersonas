package com.bramh.pruebaPersonas.Utils;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ApiResponseError extends Response {

    public ApiResponseError(String message, Integer code, HttpStatus status, LocalDateTime timestamp) {
        super(message, code, status, timestamp);
    }
}
