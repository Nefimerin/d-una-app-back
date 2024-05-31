package com.d.una.app.back.domain;

import com.d.una.app.back.model.enums.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderRequestDto {
    private Long orderId;
    private Double totalPrice;
    private Long userId;
    private OrderStatusEnum status;
    private List<Long> productsIds;
}
