package com.fiap.techChallenge.domain.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExists extends BaseHttpException{

    public ResourceAlreadyExists(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
