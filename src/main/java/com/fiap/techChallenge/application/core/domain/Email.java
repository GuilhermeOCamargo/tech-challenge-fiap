package com.fiap.techChallenge.application.core.domain;

import com.fiap.techChallenge.application.core.util.EmailValidatorUtils;

public record Email(String value) {
    public Email{
        EmailValidatorUtils.validate(value);
    }
}
