package com.fiap.techChallenge.infra.inbound.dto;

import com.fiap.techChallenge.application.core.domain.Customer;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto {

    private Long id;
    private String cpf;
    private String email;
    private String name;

    public static CustomerDto of(Customer customer) {
        return CustomerDto.builder()
                .id(customer.id())
                .cpf(customer.cpf().value())
                .email(customer.email().value())
                .name(customer.name())
                .build();
    }

    public Customer toDomain() {
        return Customer.builder()
                .id(this.getId())
                .cpf(this.getCpf())
                .email(this.getEmail())
                .name(this.getName())
                .build();
    }
}
