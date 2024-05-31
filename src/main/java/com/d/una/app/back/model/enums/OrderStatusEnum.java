package com.d.una.app.back.model.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    RECEIVED("Recibido"),
    PROCESSING("Procesando"),
    DELIVERED("Entregado"),
    CANCELLED ("Cancelado");

    private final String description;
    OrderStatusEnum(String description) {
        this.description = description;
    }
}
