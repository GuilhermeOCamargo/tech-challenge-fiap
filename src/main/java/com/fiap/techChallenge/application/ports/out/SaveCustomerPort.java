package com.fiap.techChallenge.application.ports.out;

import com.fiap.techChallenge.application.core.domain.Customer;

public interface SaveCustomerPort {

    Customer save(Customer customer);
}