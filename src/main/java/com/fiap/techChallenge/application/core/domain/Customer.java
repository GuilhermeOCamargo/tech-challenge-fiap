package com.fiap.techChallenge.application.core.domain;

import lombok.Builder;

public record Customer(String uuid, Cpf cpf, Email email, String name) {
    @Builder
    public Customer(String uuid, String cpf, String email, String name) {
        this.Customer(uuid, new Cpf(cpf), new Email(email), name);
    }
}