package com.fiap.techChallenge.domain.exceptions;

public class PaymentNotAuthorizedException extends RuntimeException{
    public PaymentNotAuthorizedException(String message) {
        super(message);
    }
}
