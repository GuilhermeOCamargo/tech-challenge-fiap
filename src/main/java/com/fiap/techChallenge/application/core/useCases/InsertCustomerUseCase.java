package com.fiap.techChallenge.application.core.useCases;

import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.application.core.exceptions.CustomerAlreadyExistsException;
import com.fiap.techChallenge.application.ports.in.InsertCustomerCasePort;
import com.fiap.techChallenge.application.ports.out.FindCustomerByCpfPort;
import com.fiap.techChallenge.application.ports.out.SaveCustomerPort;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class InsertCustomerUseCase implements InsertCustomerCasePort {

    private SaveCustomerPort saveCustomerPort;
    private FindCustomerByCpfPort findCustomerByCpfPort;

    @Override
    public Customer insert(Customer customer) throws CustomerAlreadyExistsException {
        if(Objects.nonNull(findCustomerByCpfPort.find(customer.cpf())))
            throw new CustomerAlreadyExistsException();

        return saveCustomerPort.save(customer);
    }
}
