package com.fiap.techChallenge.domain;


import com.fiap.techChallenge.util.EmailValidatorUtils;

public record Email(String value) {
    public Email {
        EmailValidatorUtils.validate(value);
    }
}
