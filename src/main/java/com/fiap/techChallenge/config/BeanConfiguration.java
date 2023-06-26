package com.fiap.techChallenge.config;

import com.fiap.techChallenge.application.core.service.CustomerInService;
import com.fiap.techChallenge.infra.outbound.adapters.CustomerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CustomerInService saveUserService(CustomerAdapter customerAdapter) {
        return new CustomerInService(customerAdapter);
    }

}
