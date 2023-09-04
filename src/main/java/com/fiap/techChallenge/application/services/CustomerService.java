package com.fiap.techChallenge.application.services;


import com.fiap.techChallenge.domain.Cpf;
import com.fiap.techChallenge.domain.Customer;

public interface CustomerService {

    Customer findByCpf(Cpf cpf);

    Customer save(Customer customer);
}
