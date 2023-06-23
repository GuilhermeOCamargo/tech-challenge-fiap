package com.fiap.techChallenge.application.core.domain;

import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.google.common.base.Strings;

public record Email(String value) {
    public Email{
        if(Strings.isNullOrEmpty(value))
            throw new InvalidDataException("Email inválido");
    }
}
