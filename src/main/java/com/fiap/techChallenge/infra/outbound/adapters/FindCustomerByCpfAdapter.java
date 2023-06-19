package com.fiap.techChallenge.infra.outbound.adapters;

import com.fiap.techChallenge.application.core.domain.Cpf;
import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.application.ports.out.FindCustomerByCpfPort;
import com.fiap.techChallenge.infra.outbound.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FindCustomerByCpfAdapter implements FindCustomerByCpfPort {

    private final CustomerRepository repository;

    @Override
    public Customer find(Cpf cpf) {
        var userEntity = repository.findByCpf(cpf.value());
        return userEntity.isPresent() ? userEntity.get().toDomain() : null;
    }
}
