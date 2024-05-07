package br.com.techChallenge.core.domain.order.enums;

import lombok.Getter;

@Getter
public enum StatusOrder {
    INICIALIZED("Iniciado"),
    IN_PROGRESS("Em progresso"),
    READY_FOR_PICKUP("Pronto para retirada"),
    OUT_FOR_DELIVERY("Saiu para entrega"),
    DELIVERED("Entregue"),
    CANCELED("Cancelado");

    private final String description;

    StatusOrder(String description) {
        this.description = description;
    }
}
