package com.fiap.techChallenge.application.core.domain;

import com.fiap.techChallenge.application.core.util.CpfValidatorUtil;

public record Cpf(String value) {
    public Cpf{
        CpfValidatorUtil.validate(value);
    }

}