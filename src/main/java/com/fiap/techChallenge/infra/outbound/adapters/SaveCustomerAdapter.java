package com.fiap.techChallenge.infra.outbound.adapters;

import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.application.ports.out.SaveCustomerPort;
import com.fiap.techChallenge.infra.inbound.entity.CustomerEntity;
import com.fiap.techChallenge.infra.outbound.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SaveCustomerAdapter implements SaveCustomerPort {

    private final CustomerRepository repository;

    @Override
    public Customer save(Customer customer) {
        return repository.save(new CustomerEntity(customer)).toDomain();
    }
}
