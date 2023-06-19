package com.fiap.techChallenge.infra.inbound.dto;

import com.fiap.techChallenge.application.core.domain.Customer;

public record CustomerDto(String cpf, String email, String name) {

    public Customer toDomain() {
        return Customer.builder()
                .cpf(this.cpf())
                .email(this.email())
                .name(this.name)
                .build();
    }
}
