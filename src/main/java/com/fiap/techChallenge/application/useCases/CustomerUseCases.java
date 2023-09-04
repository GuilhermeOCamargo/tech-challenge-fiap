package com.fiap.techChallenge.application.useCases;

import com.fiap.techChallenge.application.services.CustomerService;
import com.fiap.techChallenge.domain.Cpf;
import com.fiap.techChallenge.domain.exceptions.AlreadyExistsException;
import com.fiap.techChallenge.domain.exceptions.NotFoundException;
import com.fiap.techChallenge.presentation.dtos.CustomerDto;
import com.fiap.techChallenge.util.ConstantsUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CustomerUseCases {

    @Autowired
    private final CustomerService service;

    public CustomerDto findByCpf(@Valid @CPF String cpf) throws NotFoundException {
        var customer = service.findByCpf(new Cpf(cpf));
        if (Objects.isNull(customer))
            throw new NotFoundException(ConstantsUtil.CUSTOMER_NOT_FOUND);

        return CustomerDto.of(customer);
    }

    public CustomerDto insert(CustomerDto userDto) throws AlreadyExistsException {
        var customer = userDto.toDomain();

        if (Objects.nonNull(service.findByCpf(customer.cpf())))
            throw new AlreadyExistsException(ConstantsUtil.ALREADY_EXISTS_CUSTOMER);

        customer = service.save(customer.emptyId());

        return CustomerDto.of(customer);
    }
}
