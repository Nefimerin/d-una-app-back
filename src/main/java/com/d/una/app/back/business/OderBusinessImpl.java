package com.d.una.app.back.business;

import com.d.una.app.back.config.MessageLoader;
import com.d.una.app.back.domain.OrderRequestDto;
import com.d.una.app.back.domain.OrderResponseDto;
import com.d.una.app.back.domain.ResponseDto;
import com.d.una.app.back.exception.BusinessRuleException;
import com.d.una.app.back.mapper.IOrderMapper;
import com.d.una.app.back.model.Order;
import com.d.una.app.back.model.Product;
import com.d.una.app.back.model.User;
import com.d.una.app.back.model.enums.OrderStatusEnum;
import com.d.una.app.back.repository.IOrderRepository;
import com.d.una.app.back.repository.IProductRepository;
import com.d.una.app.back.repository.IUserRepository;
import com.d.una.app.back.util.MessagesConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__ (@Autowired))
public class OderBusinessImpl implements IOrderBusiness{
    private static final Logger logger = LoggerFactory.getLogger(OderBusinessImpl.class);
    private final IOrderRepository orderRepository;
    private final IOrderMapper orderMapper;
    private final IProductRepository productRepository;
    private final IUserRepository userRepository;

    @Override
    public ResponseDto<List<OrderResponseDto>> findAllOrders() {
        logger.info("Fetching all orders");
        List<Order> orderList = orderRepository.findAll();
        List<OrderResponseDto> orderRequestDtoList = orderList.stream()
                .map(orderMapper::toDtoResponse)
                .toList();
        return new ResponseDto<>(HttpStatus.OK.value(), MessageLoader.getInstance().getMessage(MessagesConstants.IM001), orderRequestDtoList);
    }

    @Override
    public ResponseDto<OrderResponseDto> getOrderById(Long orderId) {
        logger.info("Fetching order by ID: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM001, MessageLoader.getInstance().getMessage(MessagesConstants.EM001, orderId)));
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001), orderMapper.toDtoResponse(order));
    }

    @Transactional
    @Override
    public ResponseDto<OrderResponseDto> createOrder(OrderRequestDto orderRequestDto) {
        Order order = orderMapper.toDomain(orderRequestDto);
        List<Long> productIds = orderRequestDto.getProductsIds();
        List<Product> products = productRepository.findAllById(productIds);
        User user = userRepository.findById(orderRequestDto.getUserId())
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM008, MessageLoader.getInstance().getMessage(MessagesConstants.EM008, orderRequestDto.getUserId())));        order.setProducts(products);
        order.setStatus(OrderStatusEnum.RECEIVED);
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setOrderTime(LocalTime.now());
        order.setProducts(products);
        products.forEach(e -> e.setStock(e.getStock() - 1));
        this.productRepository.saveAll(products);
        final var orderSaved = orderRepository.save(order);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM002), orderMapper.toDtoResponse(orderSaved));
    }

    @Override
    public ResponseDto<OrderResponseDto> updateOrder(Long orderId, OrderRequestDto orderRequestDto) {
        logger.info("Updating order with ID: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM001, MessageLoader.getInstance().getMessage(MessagesConstants.EM001, orderId)));
        Order updatedOrder = orderRepository.save(order);
        OrderResponseDto updatedOrderRequestDto = orderMapper.toDtoResponse(updatedOrder);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001), updatedOrderRequestDto);
    }

    @Override
    public ResponseDto<Void> deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM001, MessageLoader.getInstance().getMessage(MessagesConstants.EM001, orderId));

        }
        orderRepository.deleteById(orderId);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001));
    }
}
