package com.fiap.techChallenge.application.core.service;

import com.fiap.techChallenge.application.core.domain.Cpf;
import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.application.core.exceptions.CustomerAlreadyExistsException;
import com.fiap.techChallenge.application.core.exceptions.CustomerNotFoundException;
import com.fiap.techChallenge.application.ports.in.CustomerInPort;
import com.fiap.techChallenge.application.ports.out.CustomerOutPort;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class CustomerInService implements CustomerInPort {

    private final CustomerOutPort port;

    @Override
    public Customer findByCpf(Cpf cpf) throws CustomerNotFoundException {
        var customer = port.findByCpf(cpf);
        if(Objects.isNull(customer))
            throw new CustomerNotFoundException();

        return customer;
    }

    @Override
    public Customer insert(Customer customer) throws CustomerAlreadyExistsException {
        if(Objects.nonNull(port.findByCpf(customer.cpf())))
            throw new CustomerAlreadyExistsException();
        return port.save(customer.emptyId());
    }
}
