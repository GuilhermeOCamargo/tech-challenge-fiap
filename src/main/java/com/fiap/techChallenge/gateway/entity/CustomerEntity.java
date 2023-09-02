package com.fiap.techChallenge.gateway.entity;

import com.fiap.techChallenge.domain.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String email;
    private String name;

    public CustomerEntity(Customer customer) {
        this(customer.id(), customer.cpf().value(), customer.email().value(), customer.name());
    }

    public Customer toDomain() {
        return Customer.builder()
                .id(this.id)
                .cpf(this.getCpf())
                .email(this.getEmail())
                .name(this.name)
                .build();
    }
}