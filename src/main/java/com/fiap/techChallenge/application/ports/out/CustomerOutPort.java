package com.fiap.techChallenge.application.ports.out;

import com.fiap.techChallenge.application.core.domain.Cpf;
import com.fiap.techChallenge.application.core.domain.Customer;

public interface CustomerOutPort {
    Customer findByCpf(Cpf cpf);
    Customer save(Customer customer);

}
