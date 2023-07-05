package com.fiap.techChallenge.application.ports.in;

import com.fiap.techChallenge.application.core.domain.Cpf;
import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.application.core.exceptions.AlreadyExistsException;
import com.fiap.techChallenge.application.core.exceptions.NotFoundException;

public interface CustomerInPort {

    Customer findByCpf(Cpf cpf) throws NotFoundException;
    Customer insert(Customer customer) throws AlreadyExistsException;
}
