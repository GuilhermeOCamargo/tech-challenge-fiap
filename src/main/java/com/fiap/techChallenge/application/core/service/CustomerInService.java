package com.fiap.techChallenge.application.core.service;

import com.fiap.techChallenge.application.core.domain.Cpf;
import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.application.core.exceptions.AlreadyExistsException;
import com.fiap.techChallenge.application.core.exceptions.NotFoundException;
import com.fiap.techChallenge.application.ports.in.CustomerInPort;
import com.fiap.techChallenge.application.ports.out.CustomerOutPort;
import lombok.AllArgsConstructor;

import java.util.Objects;

import static com.fiap.techChallenge.application.core.util.ConstantsUtil.ALREADY_EXISTS_USER;
import static com.fiap.techChallenge.application.core.util.ConstantsUtil.CUSTOMER_NOT_FOUND;

@AllArgsConstructor
public class CustomerInService implements CustomerInPort {

    private final CustomerOutPort port;

    @Override
    public Customer findByCpf(Cpf cpf) throws NotFoundException {
        var customer = port.findByCpf(cpf);
        if(Objects.isNull(customer))
            throw new NotFoundException(CUSTOMER_NOT_FOUND);

        return customer;
    }

    @Override
    public Customer insert(Customer customer) throws AlreadyExistsException {
        if(Objects.nonNull(port.findByCpf(customer.cpf())))
            throw new AlreadyExistsException(ALREADY_EXISTS_USER);

        return port.save(customer);
    }
}
