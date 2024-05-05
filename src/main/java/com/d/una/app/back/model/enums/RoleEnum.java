package com.d.una.app.back.model.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    DELIVERY("Repartidor"),
    ADMINISTRATOR("Administrador"),
    CUSTOMER("Cliente");
    private final String description;

    RoleEnum(String description) {
        this.description = description;
    }


}
