package com.fiap.techChallenge.application.ports.in;

import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.application.core.exceptions.CustomerAlreadyExistsException;

public interface InsertCustomerCasePort {
    Customer insert(Customer customer) throws CustomerAlreadyExistsException;
}
