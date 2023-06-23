package com.fiap.techChallenge.application.core.domain;

import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.google.common.base.Strings;

public record Cpf(String value) {
    public Cpf{
        if(Strings.isNullOrEmpty(value))
            throw new InvalidDataException("Cpf inválido");
    }
}