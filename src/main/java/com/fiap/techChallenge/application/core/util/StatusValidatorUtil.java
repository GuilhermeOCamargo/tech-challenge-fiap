package com.fiap.techChallenge.application.core.util;

import com.fiap.techChallenge.application.core.domain.Status;
import com.fiap.techChallenge.application.core.enums.StatusEnum;
import com.fiap.techChallenge.application.core.exceptions.InvalidDataException;
import com.google.common.base.Strings;

public class StatusValidatorUtil {

    private final static String INVALID_STATUS = "Invalid Status";
    
    private StatusValidatorUtil(){

    }

    public static void validate(String status){
        if(Strings.isNullOrEmpty(status)){
            throw new InvalidDataException(INVALID_STATUS + ": status must not be null or empty");
        }

        if(!status.equals(status.toLowerCase())){
            throw new InvalidDataException(INVALID_STATUS + ": status must not contain uppercase letters");
        }

        if(!StatusEnum.isValidStatus(status)){
            throw new InvalidDataException(INVALID_STATUS + ": status must be in " + StatusEnum.getValidStatus() );
        }
    }
}
