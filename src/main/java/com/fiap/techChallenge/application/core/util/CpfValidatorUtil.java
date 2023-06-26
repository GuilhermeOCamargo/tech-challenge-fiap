package com.fiap.techChallenge.application.core.util;

import br.com.caelum.stella.validation.CPFValidator;
import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.google.common.base.Strings;

import java.util.Objects;

public class CpfValidatorUtil {

    private static String ERROR_MESSAGE = "Cpf inválido";

    private static CPFValidator INSTANCE;

    private CpfValidatorUtil() {
    }

    private static CPFValidator getInstance() {
        if(Objects.isNull(INSTANCE))
            INSTANCE = new CPFValidator(false);
        return INSTANCE;
    }

    public static void validate(String cpf) {
        if(Strings.isNullOrEmpty(cpf))
            throw new InvalidDataException(ERROR_MESSAGE);
        try {
            getInstance().assertValid(cpf);
        } catch (Exception e) {
            throw new InvalidDataException(ERROR_MESSAGE);
        }
    }
}
