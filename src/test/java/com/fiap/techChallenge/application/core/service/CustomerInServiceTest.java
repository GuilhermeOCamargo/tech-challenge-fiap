package com.fiap.techChallenge.application.core.service;

import com.fiap.techChallenge.application.core.domain.Customer;
import com.fiap.techChallenge.application.core.exceptions.AlreadyExistsException;
import com.fiap.techChallenge.application.core.exceptions.NotFoundException;
import com.fiap.techChallenge.application.ports.out.CustomerOutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerInServiceTest {

    @Mock
    private CustomerOutPort port;
    @InjectMocks
    private CustomerInService service;

    private Customer CUSTOMER = buildCustomer();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidCustomer_whenSave_thenSaveAndReturnCustomer(){
        when(port.findByCpf(CUSTOMER.cpf())).thenReturn(null);
        when(port.save(CUSTOMER)).thenReturn(CUSTOMER);

        var savedCustomer = service.insert(CUSTOMER);
        assertNotNull(savedCustomer);
        verify(port, times(1)).findByCpf(any());
        verify(port, times(1)).save(CUSTOMER);
    }

    @Test
    public void givenValidCustomer_whenSave_thenThrowCustomerExists() {
        when(port.findByCpf(CUSTOMER.cpf())).thenReturn(CUSTOMER);
        assertThrowsExactly(AlreadyExistsException.class, () -> service.insert(CUSTOMER));
        verify(port, times(1)).findByCpf(any());
        verify(port, times(0)).save(CUSTOMER);
    }

    @Test
    public void givenValidCpf_whenFindByCpf_thenFindAndReturnCustomer(){
        when(port.findByCpf(CUSTOMER.cpf())).thenReturn(CUSTOMER);

        var foundCustomer = service.findByCpf(CUSTOMER.cpf());
        assertNotNull(foundCustomer);
        verify(port, times(1)).findByCpf(any());
    }

    @Test
    public void givenValidCpf_whenFindByCpf_thenThrowCustomerNotFound(){
        when(port.findByCpf(CUSTOMER.cpf())).thenReturn(null);
        assertThrowsExactly(NotFoundException.class, () -> service.findByCpf(CUSTOMER.cpf()));
        verify(port, times(1)).findByCpf(any());
    }

    private Customer buildCustomer(){
        return Customer.builder()
                .cpf("52030599000")
                .email("teste@teste.com")
                .name("Teste de teste")
                .build();
    }

}
