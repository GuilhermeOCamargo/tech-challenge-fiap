package com.fiap.techChallenge.infra.outbound.adapters;

import com.fiap.techChallenge.application.core.domain.Cpf;
import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.application.ports.out.CustomerOutPort;
import com.fiap.techChallenge.infra.outbound.repository.mariadb.CustomerRepository;
import com.fiap.techChallenge.infra.outbound.repository.mariadb.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerAdapter implements CustomerOutPort {

    private final CustomerRepository repository;

    @Override
    public Customer findByCpf(Cpf cpf) {
        var userEntity = repository.findByCpf(cpf.value());
        return userEntity.isPresent() ? userEntity.get().toDomain() : null;
    }

    @Override
    public Customer save(Customer customer) {
        return repository.save(new CustomerEntity(customer)).toDomain();
    }
}
