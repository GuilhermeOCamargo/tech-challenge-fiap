package com.fiap.techChallenge.infra.inbound.service.impl;

import com.fiap.techChallenge.application.core.domain.Cpf;
import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.application.core.exceptions.CustomerAlreadyExistsException;
import com.fiap.techChallenge.application.core.exceptions.CustomerNotFoundException;
import com.fiap.techChallenge.application.ports.in.FindCustomerByCpfCasePort;
import com.fiap.techChallenge.application.ports.in.InsertCustomerCasePort;
import com.fiap.techChallenge.infra.inbound.dto.CustomerDto;
import com.fiap.techChallenge.infra.inbound.exception.ResourceAlreadyExists;
import com.fiap.techChallenge.infra.inbound.exception.ResourceNotFoundException;
import com.fiap.techChallenge.infra.inbound.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {

    private final InsertCustomerCasePort insertUserServiceCasePort;
    private final FindCustomerByCpfCasePort findUserByCpfCasePort;

    public Customer insert(CustomerDto userDto) {
        try{
            return insertUserServiceCasePort.insert(userDto.toDomain());
        } catch (CustomerAlreadyExistsException e) {
            throw new ResourceAlreadyExists(e.getMessage());
        }
    }

    public Customer findByCpf(String cpf) {
        try{
            return findUserByCpfCasePort.find(new Cpf(cpf));
        } catch (CustomerNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

}
