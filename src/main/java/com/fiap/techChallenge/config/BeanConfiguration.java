package com.fiap.techChallenge.config;

import com.fiap.techChallenge.application.core.useCases.FindCustomerByCpfUseCase;
import com.fiap.techChallenge.application.core.useCases.InsertCustomerUseCase;
import com.fiap.techChallenge.infra.outbound.adapters.FindCustomerByCpfAdapter;
import com.fiap.techChallenge.infra.outbound.adapters.SaveCustomerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public InsertCustomerUseCase saveUserService(SaveCustomerAdapter saveUserAdapter, FindCustomerByCpfAdapter findUserByCpfAdapter) {
        return new InsertCustomerUseCase(saveUserAdapter, findUserByCpfAdapter);
    }

    @Bean
    public FindCustomerByCpfUseCase findUserByCpfUseCase(FindCustomerByCpfAdapter findUserByCpfAdapter) {
        return new FindCustomerByCpfUseCase(findUserByCpfAdapter);
    }
}
