package com.fiap.techChallenge.application.services.impl;

import com.fiap.techChallenge.application.services.CustomerService;
import com.fiap.techChallenge.domain.Cpf;
import com.fiap.techChallenge.domain.Customer;
import com.fiap.techChallenge.gateway.entity.CustomerEntity;
import com.fiap.techChallenge.gateway.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository repository;

    @Override
    public Customer findByCpf(Cpf cpf) {
        var userEntity = repository.findByCpf(cpf.value());
        return userEntity.isPresent() ? userEntity.get().toDomain() : null;
    }

    @Override
    public Customer save(Customer customer) {
        return repository.save(new CustomerEntity(customer)).toDomain();
    }
}