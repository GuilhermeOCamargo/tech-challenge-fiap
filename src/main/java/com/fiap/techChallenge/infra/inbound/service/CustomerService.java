package com.fiap.techChallenge.infra.inbound.service;

import com.fiap.techChallenge.infra.inbound.dto.CustomerDto;

public interface CustomerService {

    CustomerDto insert(CustomerDto customerDto);
    CustomerDto findByCpf(String cpf);
}
