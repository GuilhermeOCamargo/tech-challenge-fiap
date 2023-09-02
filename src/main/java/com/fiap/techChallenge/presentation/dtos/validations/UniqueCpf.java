package com.fiap.techChallenge.presentation.dtos.validations;

import com.fiap.techChallenge.presentation.dtos.validations.impl.UniqueCpfImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCpfImpl.class)
public @interface UniqueCpf {
    String message() default "Cpf já cadastrado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
    String name() default "";
    String id() default "";
}
