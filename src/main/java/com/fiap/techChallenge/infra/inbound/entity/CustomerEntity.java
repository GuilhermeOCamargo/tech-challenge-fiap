package com.fiap.techChallenge.infra.inbound.entity;

import com.fiap.techChallenge.application.core.domain.Customer;
import com.google.common.base.Strings;

import java.util.UUID;

public record CustomerEntity(String uuid, String cpf, String email, String name) {

    public CustomerEntity {
        if(Strings.isNullOrEmpty(uuid))
            uuid = UUID.randomUUID().toString();
    }

    public CustomerEntity(Customer customer) {
        this(customer.uuid(), customer.name(), customer.email().value(), customer.cpf().value());
    }

    public Customer toDomain() {
        return Customer.builder()
                .uuid(this.uuid)
                .cpf(this.cpf())
                .email(this.email())
                .name(this.name)
                .build();
    }
}