package com.fiap.techChallenge.infra.inbound.enums;

import com.fiap.techChallenge.application.core.enums.StatusEnum;

public enum PaymentStatusEnum {

    APROVADO(StatusEnum.PAGAMENTO_CONFIRMADO.name().toLowerCase()),
    RECUSADO(StatusEnum.PAGAMENTO_RECUSADO.name().toLowerCase());

    private final String status;

    private PaymentStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static PaymentStatusEnum valueOfIgnoreCase(String paymentStatus) {
        return PaymentStatusEnum.valueOf(paymentStatus.toUpperCase());
    }



}
