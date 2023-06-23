package com.fiap.techChallenge.infra.inbound.dto;

import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.infra.inbound.dto.validations.UniqueCpf;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record CustomerDto(@NotNull @NotEmpty @CPF String cpf,
                          @NotNull @NotEmpty @Email String email,
                          @NotNull @NotEmpty String name) {

    public Customer toDomain() {
        return Customer.builder()
                .cpf(this.cpf())
                .email(this.email())
                .name(this.name)
                .build();
    }
}
