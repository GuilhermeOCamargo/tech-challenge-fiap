package com.fiap.techChallenge.domain;

import com.fiap.techChallenge.util.CpfValidatorUtil;

public record Cpf(String value) {
    public Cpf{
        CpfValidatorUtil.validate(value);
    }
}