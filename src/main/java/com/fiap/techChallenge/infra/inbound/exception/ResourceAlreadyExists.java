package com.fiap.techChallenge.infra.inbound.exception;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExists extends BaseHttpException{

    public ResourceAlreadyExists(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
