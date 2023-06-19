package com.fiap.techChallenge.infra.inbound.controller;

import com.fiap.techChallenge.infra.inbound.dto.ErrorResponseDto;
import com.fiap.techChallenge.infra.inbound.exception.BaseHttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(BaseHttpException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpException(BaseHttpException e) {
        return ResponseEntity.status(e.getStatusCode()).body(new ErrorResponseDto(e.getMessage()));
    }

}
