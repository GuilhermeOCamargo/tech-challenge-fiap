package com.fiap.techChallenge.domain;

import com.fiap.techChallenge.domain.exceptions.InvalidDataException;
import com.google.common.base.Strings;

import lombok.Builder;

public record Customer(Long id, Cpf cpf, Email email, String name) {
    public Customer {
        if (Strings.isNullOrEmpty(name))
            throw new InvalidDataException("Nome inválido");
    }

    @Builder
    public Customer(Long id, String cpf, String email, String name) {
        this(id, new Cpf(cpf), new Email(email), name);
    }

    public Customer emptyId() {
        return new Customer(null, cpf, email, name);
    }
}