package com.fiap.techChallenge.infra.inbound.exception;

import org.springframework.http.HttpStatus;

public class PaymentException extends BaseHttpException
{
    public PaymentException(String message) {
        super(HttpStatus.PAYMENT_REQUIRED, message);
    }
}
