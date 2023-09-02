package com.fiap.techChallenge.presentation.dtos.validations.impl;

import com.fiap.techChallenge.application.services.CustomerService;
import com.fiap.techChallenge.domain.Cpf;
import com.fiap.techChallenge.domain.exceptions.NotFoundException;
import com.fiap.techChallenge.presentation.dtos.validations.UniqueCpf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class UniqueCpfImpl implements ConstraintValidator<UniqueCpf, String> {
    @Autowired
    private CustomerService customerService;
    private String message;

    @Override
    public void initialize(UniqueCpf constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            customerService.findByCpf(new Cpf(value));
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message);
            return false;
        } catch (NotFoundException e) {
            return true;
        }
    }
}
