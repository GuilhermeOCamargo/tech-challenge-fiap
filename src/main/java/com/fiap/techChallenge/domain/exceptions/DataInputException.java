package com.fiap.techChallenge.domain.exceptions;

import org.springframework.http.HttpStatus;

public class DataInputException extends BaseHttpException{
    public DataInputException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }
}