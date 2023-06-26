package com.fiap.techChallenge.infra.inbound.service;

import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.infra.inbound.dto.CustomerDto;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;

public interface CustomerService {

    CustomerDto insert(CustomerDto customerDto);
    CustomerDto findByCpf(String cpf);
}
