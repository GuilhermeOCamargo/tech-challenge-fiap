package com.fiap.techChallenge.application.core.domain;

import com.fiap.techChallenge.application.core.enums.StatusEnum;
import com.fiap.techChallenge.application.core.util.StatusValidatorUtil;

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
