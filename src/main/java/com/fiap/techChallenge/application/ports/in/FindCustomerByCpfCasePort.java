package com.fiap.techChallenge.application.ports.in;

import com.fiap.techChallenge.application.core.domain.Cpf;
import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.application.core.exceptions.NotFoundException;

public interface FindCustomerByCpfCasePort {

    Customer find(Cpf cpf) throws NotFoundException;
}
