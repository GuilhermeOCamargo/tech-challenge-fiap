package com.fiap.techChallenge.application.core.useCases;

import com.fiap.techChallenge.application.services.CustomerService;
import com.fiap.techChallenge.application.useCases.CustomerUseCases;
import com.fiap.techChallenge.domain.Customer;
import com.fiap.techChallenge.domain.exceptions.AlreadyExistsException;
import com.fiap.techChallenge.domain.exceptions.NotFoundException;
import com.fiap.techChallenge.presentation.dtos.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerUseCasesTest {

    @Mock
    private CustomerService service;
    @InjectMocks
    private CustomerUseCases customerUseCases;

    private Customer CUSTOMER = buildCustomer();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidCustomer_whenSave_thenSaveAndReturnCustomer() {
        when(service.findByCpf(CUSTOMER.cpf())).thenReturn(null);
        when(service.save(CUSTOMER)).thenReturn(CUSTOMER);

        var savedCustomer = customerUseCases.insert(CustomerDto.of(CUSTOMER));
        assertNotNull(savedCustomer);
        verify(service, times(1)).findByCpf(any());
        verify(service, times(1)).save(CUSTOMER);
    }

    @Test
    public void givenValidCustomer_whenSave_thenThrowCustomerExists() {
        when(service.findByCpf(CUSTOMER.cpf())).thenReturn(CUSTOMER);
        assertThrowsExactly(AlreadyExistsException.class, () -> customerUseCases.insert(CustomerDto.of(CUSTOMER)));
        verify(service, times(1)).findByCpf(any());
        verify(service, times(0)).save(CUSTOMER);
    }

    @Test
    public void givenValidCpf_whenFindByCpf_thenFindAndReturnCustomer() {
        when(service.findByCpf(CUSTOMER.cpf())).thenReturn(CUSTOMER);

        var foundCustomer = customerUseCases.findByCpf("52030599000");
        assertNotNull(foundCustomer);
        verify(service, times(1)).findByCpf(any());
    }

    @Test
    public void givenValidCpf_whenFindByCpf_thenThrowCustomerNotFound() {
        when(service.findByCpf(CUSTOMER.cpf())).thenReturn(null);
        assertThrowsExactly(NotFoundException.class, () -> customerUseCases.findByCpf("52030599000"));
        verify(service, times(1)).findByCpf(any());
    }

    private Customer buildCustomer() {
        return Customer.builder()
                .cpf("52030599000")
                .email("teste@teste.com")
                .name("Teste de teste")
                .build();
    }

}
