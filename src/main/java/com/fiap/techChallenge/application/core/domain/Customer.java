package com.fiap.techChallenge.application.core.domain;

import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.google.common.base.Strings;
import lombok.Builder;

public record Customer(Long id, Cpf cpf, Email email, String name) {

    public Customer{
        if(Strings.isNullOrEmpty(name))
            throw new InvalidDataException("Nome inválido");
    }

    @Builder
    public Customer(Long id, String cpf, String email, String name) {
        this(id, new Cpf(cpf), new Email(email), name);
    }
}