package com.fiap.techChallenge.domain.exceptions;

import org.springframework.http.HttpStatus;

public class PaymentException extends BaseHttpException
{
    public PaymentException(String message) {
        super(HttpStatus.PAYMENT_REQUIRED, message);
    }
}
