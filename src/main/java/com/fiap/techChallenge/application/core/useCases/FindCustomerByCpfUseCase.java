package com.fiap.techChallenge.application.core.useCases;

import com.fiap.techChallenge.application.core.domain.Cpf;
import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.application.core.exceptions.CustomerNotFoundException;
import com.fiap.techChallenge.application.ports.in.FindCustomerByCpfCasePort;
import com.fiap.techChallenge.application.ports.out.FindCustomerByCpfPort;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class FindCustomerByCpfUseCase implements FindCustomerByCpfCasePort {

    private FindCustomerByCpfPort findUserByCpfPort;

    @Override
    public Customer find(Cpf cpf) throws CustomerNotFoundException {
        var customer = findUserByCpfPort.find(cpf);
        if(Objects.isNull(customer))
            throw new CustomerNotFoundException();

        return customer;
    }
}
