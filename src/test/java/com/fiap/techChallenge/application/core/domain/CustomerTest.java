package com.fiap.techChallenge.application.core.domain;

import com.fiap.techChallenge.domain.Customer;
import com.fiap.techChallenge.domain.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private String NAME_MESSAGE = "Nome inválido";
    private String CPF_MESSAGE = "Cpf inválido";
    private String EMAIL_MESSAGE = "Email inválido";


    @Test
    public void givenValidaParams_whenNewCustomer_thenThrowInvalidData() {
        var customer = Customer.builder()
                        .cpf("52030599000")
                        .email("teste@teste.com")
                        .name("Teste")
                        .build();

        assertNotNull(customer);
    }

    @Test
    public void givenInvalidCpf_whenNewCustomer_thenThrowInvalidData() {
        var exception = assertThrows(InvalidDataException.class, () ->
                Customer.builder()
                        .cpf("naondas")
                        .build()
                );

        assertEquals(CPF_MESSAGE, exception.getMessage());
    }

    @Test
    public void givenInvalidEmail_whenNewCustomer_thenThrowInvalidData() {
        var exception = assertThrows(InvalidDataException.class, () ->
                Customer.builder()
                        .cpf("52030599000")
                        .email("disoad")
                        .build()
        );

        assertEquals(EMAIL_MESSAGE, exception.getMessage());
    }

    @Test
    public void givenInvalidName_whenNewCustomer_thenThrowInvalidData() {
        var exception = assertThrows(InvalidDataException.class, () ->
                Customer.builder()
                        .cpf("52030599000")
                        .email("teste@teste.com")
                        .build()
        );

        assertEquals(NAME_MESSAGE, exception.getMessage());
    }
}
