package com.fiap.techChallenge.application.ports.out;

import com.fiap.techChallenge.application.core.domain.Cpf;
import com.fiap.techChallenge.application.core.domain.Customer;

public interface FindCustomerByCpfPort {

    Customer find(Cpf cpf);
}