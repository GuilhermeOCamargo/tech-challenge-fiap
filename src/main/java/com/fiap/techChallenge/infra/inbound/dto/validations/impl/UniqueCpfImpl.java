package com.fiap.techChallenge.infra.inbound.dto.validations.impl;

import com.fiap.techChallenge.application.core.domain.Cpf;
import com.fiap.techChallenge.application.core.exceptions.NotFoundException;
import com.fiap.techChallenge.application.ports.in.CustomerInPort;
import com.fiap.techChallenge.infra.inbound.dto.validations.UniqueCpf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCpfImpl implements ConstraintValidator<UniqueCpf, String> {
    @Autowired
    private CustomerInPort customerInPort;
    private String message;

    @Override
    public void initialize(UniqueCpf constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            customerInPort.findByCpf(new Cpf(value));
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message);
            return false;
        } catch (NotFoundException e) {
            return true;
        }
    }
}
