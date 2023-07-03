package com.fiap.techChallenge.infra.inbound.service.impl;

import com.fiap.techChallenge.application.core.domain.Cpf;
import com.fiap.techChallenge.application.core.exceptions.CustomerAlreadyExistsException;
import com.fiap.techChallenge.application.core.exceptions.CustomerNotFoundException;
import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.fiap.techChallenge.application.ports.in.CustomerInPort;
import com.fiap.techChallenge.infra.inbound.dto.CustomerDto;
import com.fiap.techChallenge.infra.inbound.exception.DataInputException;
import com.fiap.techChallenge.infra.inbound.exception.ResourceAlreadyExists;
import com.fiap.techChallenge.infra.inbound.exception.ResourceNotFoundException;
import com.fiap.techChallenge.infra.inbound.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {

    private final CustomerInPort port;

    public CustomerDto insert(@Valid CustomerDto customerDto) {
        try{
            var  customer = port.insert(customerDto.toDomain());
            return CustomerDto.of(customer);
        } catch (CustomerAlreadyExistsException e) {
            throw new ResourceAlreadyExists(e.getMessage());
        } catch (InvalidDataException e) {
            throw new DataInputException(e.getMessage());
        }
    }

    public CustomerDto findByCpf(@Valid @CPF String cpf) {
        try{
            var customer = port.findByCpf(new Cpf(cpf));
            return CustomerDto.of(customer);
        } catch (CustomerNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (InvalidDataException e) {
            throw new DataInputException(e.getMessage());
        }
    }

}
