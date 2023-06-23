package com.fiap.techChallenge.infra.outbound.repository.mariadb.entity;

import com.fiap.techChallenge.application.core.domain.Customer;
import com.google.common.base.Strings;
import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name = "customer")
public record CustomerEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) String uuid, String cpf, String email, String name) {

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