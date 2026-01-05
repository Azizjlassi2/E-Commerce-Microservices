package com.example.order.mapper;

import org.springframework.stereotype.Service;

import com.example.order.dto.request.OrderRequest;
import com.example.order.dto.response.OrderResponse;
import com.example.order.model.Order;

/**
 * OrderMapper Mapper class for converting between Order entities and DTOs.
 */
@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
        return Order.builder().id(request.getId()).customerId(request.getCustomerId())
                .reference(request.getReference()).amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod()).build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder().id(order.getId()).reference(order.getReference())
                .amount(order.getAmount()).paymentMethod(order.getPaymentMethod())
                .customerId(order.getCustomerId()).build();
    }

}
