package com.fiap.techChallenge.util;

import com.fiap.techChallenge.domain.exceptions.InvalidDataException;
import com.google.common.base.Strings;

public class EmailValidatorUtils {

    private static String REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private EmailValidatorUtils() {
    }
    public static void validate(String email) {
        if (Strings.isNullOrEmpty(email) || !email.matches(REGEX))
            throw new InvalidDataException("Email inválido");
    }
}
