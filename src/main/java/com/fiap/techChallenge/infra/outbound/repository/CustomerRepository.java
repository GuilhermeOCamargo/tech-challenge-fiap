package com.fiap.techChallenge.infra.outbound.repository;

import com.fiap.techChallenge.infra.inbound.entity.CustomerEntity;

import java.util.Optional;

public class CustomerRepository {

    public CustomerEntity save(CustomerEntity userEntity) {
        return userEntity;
    }
    public Optional<CustomerEntity> findByCpf(String cpf) {
        return Optional.of(new CustomerEntity("123", "00000000000", "abc@gmail.com", "nome de sobrenome"));
    }
}
