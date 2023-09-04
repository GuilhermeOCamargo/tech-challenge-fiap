package com.fiap.techChallenge.gateway.repository;

import com.fiap.techChallenge.gateway.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByCpf(String cpf);
}
