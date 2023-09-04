package com.fiap.techChallenge.domain;

import com.fiap.techChallenge.application.enums.StatusEnum;
import com.fiap.techChallenge.util.StatusValidatorUtil;

public record Status(String value) {

    public Status{
        StatusValidatorUtil.validate(value);
    }

    public Boolean newStatusIsValid(Status newStatus){
        StatusEnum actualStatusEnum = StatusEnum.valueOfIgnoreCase(value);
        StatusEnum newStatusEnum = StatusEnum.valueOfIgnoreCase(newStatus.value);

        if(newStatusEnum.getBeforeStatus().equals(actualStatusEnum)){
            return true;
        }

        return false;
    }
}
