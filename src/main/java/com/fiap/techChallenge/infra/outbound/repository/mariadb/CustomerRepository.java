package com.fiap.techChallenge.infra.outbound.repository.mariadb;

import com.fiap.techChallenge.infra.outbound.repository.mariadb.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByCpf(String cpf);
}
