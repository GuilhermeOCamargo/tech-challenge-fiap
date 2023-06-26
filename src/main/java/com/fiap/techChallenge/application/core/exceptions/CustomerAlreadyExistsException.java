package com.fiap.techChallenge.application.core.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException{

    public CustomerAlreadyExistsException() {
        super("Usuário já cadastrado na base de dados");
    }
}