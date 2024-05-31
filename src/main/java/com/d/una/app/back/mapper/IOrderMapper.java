package com.d.una.app.back.mapper;

import com.d.una.app.back.domain.OrderRequestDto;
import com.d.una.app.back.domain.OrderResponseDto;
import com.d.una.app.back.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface IOrderMapper extends IEntityMapper<OrderRequestDto, Order>{
    Order toDomain(OrderRequestDto dto);
    OrderRequestDto toDto(Order entity);
    OrderResponseDto toDtoResponse(Order entity);

}
