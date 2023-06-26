package com.fiap.techChallenge.application.core.exceptions;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException() {
        super("Cliente não encontrado");
    }
}
