package com.fiap.techChallenge.application.ports.in;

import com.fiap.techChallenge.application.core.domain.Cpf;
import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.application.core.exceptions.CustomerAlreadyExistsException;
import com.fiap.techChallenge.application.core.exceptions.CustomerNotFoundException;

public interface CustomerInPort {

    Customer findByCpf(Cpf cpf) throws CustomerNotFoundException;;
    Customer insert(Customer customer) throws CustomerAlreadyExistsException;
}
