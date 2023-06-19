package com.fiap.techChallenge.infra.inbound.service;

import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.infra.inbound.dto.CustomerDto;

public interface CustomerService {

    Customer insert(CustomerDto customerDto);
    Customer findByCpf(String cpf);
}
