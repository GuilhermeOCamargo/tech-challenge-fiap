package com.fiap.techChallenge.infra.inbound.exception;

import org.springframework.http.HttpStatus;

public class DataInputException extends BaseHttpException{
    public DataInputException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }
}
