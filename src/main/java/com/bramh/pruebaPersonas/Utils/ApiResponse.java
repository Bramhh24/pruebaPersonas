package com.bramh.pruebaPersonas.Utils;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ApiResponse extends Response {

    public ApiResponse(String message, Integer code, HttpStatus status, LocalDateTime timestamp){

        super(message, code, status, timestamp);
    }
}
