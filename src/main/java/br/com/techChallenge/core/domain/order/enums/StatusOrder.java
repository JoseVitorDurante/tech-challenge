package br.com.techChallenge.core.domain.order.enums;

import lombok.Getter;

@Getter
public enum StatusOrder {
    RECEIVED("Recebido"),
    IN_PREPARATION("Em preparação"),
    READY_FOR_PICKUP("Pronto para retirada"),
    OUT_FOR_DELIVERY("Saiu para entrega"),
    FINISHED("Finalizado"),
    CANCELED("Cancelado");

    private final String description;

    StatusOrder(String description) {
        this.description = description;
    }
}
