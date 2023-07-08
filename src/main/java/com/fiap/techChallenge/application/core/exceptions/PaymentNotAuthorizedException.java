package com.fiap.techChallenge.application.core.exceptions;

public class PaymentNotAuthorizedException extends RuntimeException{
    public PaymentNotAuthorizedException(String message) {
        super(message);
    }
}
