package com.d.una.app.back.rest;

import com.d.una.app.back.business.IOrderBusiness;
import com.d.una.app.back.domain.OrderRequestDto;
import com.d.una.app.back.domain.OrderResponseDto;
import com.d.una.app.back.domain.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor = @__ (@Autowired))
public class OrderRest {
    private final IOrderBusiness orderBusiness;

    @GetMapping
    public ResponseEntity<ResponseDto<List<OrderResponseDto>>> findAllOrders() {
        ResponseDto<List<OrderResponseDto>> responseDto = orderBusiness.findAllOrders();
        return responseDto.of();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDto<OrderResponseDto>> getOrderById(@PathVariable Long orderId) {
        ResponseDto<OrderResponseDto> responseDto = orderBusiness.getOrderById(orderId);
        return responseDto.of();
    }

    @PostMapping
    public ResponseEntity<ResponseDto<OrderResponseDto>> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        ResponseDto<OrderResponseDto> responseDto = orderBusiness.createOrder(orderRequestDto);
        return responseDto.of();
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ResponseDto<OrderResponseDto>> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequestDto orderRequestDto) {
        ResponseDto<OrderResponseDto> responseDto = orderBusiness.updateOrder(orderId, orderRequestDto);
        return responseDto.of();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ResponseDto<Void>> deleteOrder(@PathVariable Long orderId) {
        ResponseDto<Void> responseDto = orderBusiness.deleteOrder(orderId);
        return responseDto.of();
    }

}
