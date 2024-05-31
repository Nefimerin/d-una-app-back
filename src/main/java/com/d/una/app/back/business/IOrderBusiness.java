package com.d.una.app.back.business;

import com.d.una.app.back.domain.OrderRequestDto;
import com.d.una.app.back.domain.OrderResponseDto;
import com.d.una.app.back.domain.ResponseDto;

import java.util.List;

public interface IOrderBusiness {

    /**
     * Retrieves all orders.
     *
     * @return a ResponseDto containing a list of OrderDto objects representing all orders in the system.
     */
    ResponseDto<List<OrderResponseDto>> findAllOrders();


    /**
     * Retrieves a Order based on the provided order ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return A ResponseDto containing the OrderDto information if found, or an appropriate response if not found.
     */
    ResponseDto<OrderResponseDto> getOrderById(final Long orderId);

    /**
     * Creates a new Order using the provided OrderDto.
     *
     * @param productDto The OrderDto containing the information needed to create the new order.
     * @return A ResponseDto indicating the result of the creation process, including the newly created OrderDto.
     */
    ResponseDto<OrderResponseDto> createOrder(final OrderRequestDto productDto);

    /**
     * Updates an existing Order identified by the provided order ID using the new information in OrderDto.
     *
     * @param orderId  The ID of the order to update.
     * @param productDto The OrderDto containing the updated order information.
     * @return A ResponseDto indicating the result of the update process, including the updated OrderDto.
     */
    ResponseDto<OrderResponseDto> updateOrder(final Long orderId, final OrderRequestDto productDto);

    /**
     * Deletes a Order based on the provided order ID.
     *
     * @param orderId The ID of the order to delete.
     * @return A ResponseDto indicating the result of the deletion process.
     */
    ResponseDto<Void> deleteOrder(final Long orderId);
}
