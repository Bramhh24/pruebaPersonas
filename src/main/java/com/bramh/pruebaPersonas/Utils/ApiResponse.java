package com.bramh.pruebaPersonas.Utils;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiResponse extends Response {

    public ApiResponse(String message, Integer code, HttpStatus status, LocalDateTime timestamp, List<String> errors){

        super(message, code, status, timestamp, errors);
    }
}
