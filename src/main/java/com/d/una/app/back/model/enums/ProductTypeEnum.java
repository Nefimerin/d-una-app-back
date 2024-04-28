package com.d.una.app.back.model.enums;

import lombok.Getter;

@Getter
public enum ProductTypeEnum {
    SCREEN("Pantalla"),
    BATTERY("Batería"),
    CHARGER("Cargador"),
    CASE("Funda"),
    HEADPHONES("Auriculares"),
    CAMERA("Cámara");
    private final String description;
    ProductTypeEnum(String description) {
        this.description = description;
    }
}
