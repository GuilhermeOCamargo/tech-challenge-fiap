package com.fiap.techChallenge.application.enums;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum StatusEnum {

    INICIADO {
        @Override
        public StatusEnum getBeforeStatus() {
            return INICIADO;
        }
    },

    PAGAMENTO_SOLICITADO {
        @Override
        public StatusEnum getBeforeStatus() {
            return INICIADO;
        }
    },

    PAGAMENTO_CONFIRMADO {
        @Override
        public StatusEnum getBeforeStatus() {
            return PAGAMENTO_SOLICITADO;
        }
    },

    PAGAMENTO_RECUSADO {
        @Override
        public StatusEnum getBeforeStatus() {
            return PAGAMENTO_SOLICITADO;
        }
    },

    RECEBIDO {
        @Override
        public StatusEnum getBeforeStatus() {
            return PAGAMENTO_CONFIRMADO;
        }
    },

    EM_PREPARACAO {
        @Override
        public StatusEnum getBeforeStatus() {
            return RECEBIDO;
        }
    },

    PRONTO {
        @Override
        public StatusEnum getBeforeStatus() {
            return EM_PREPARACAO;
        }
    },

    FINALIZADO {
        @Override
        public StatusEnum getBeforeStatus() {
            return PRONTO;
        }
    };

    public static Boolean isValidStatus(String status) {
        return Stream.of(StatusEnum.values()).map(StatusEnum::name).anyMatch(status::equalsIgnoreCase);
    }

    public static Set<String> getValidStatus() {
        return Stream.of(StatusEnum.values()).map(statusEnum -> statusEnum.name().toLowerCase())
                .collect(Collectors.toSet());
    }

    public static StatusEnum valueOfIgnoreCase(String status){
        return StatusEnum.valueOf(status.toUpperCase());
    }

    public abstract StatusEnum getBeforeStatus();

}
