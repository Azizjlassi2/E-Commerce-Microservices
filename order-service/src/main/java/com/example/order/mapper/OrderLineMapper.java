package com.example.order.mapper;

import org.springframework.stereotype.Service;

import com.example.order.dto.request.OrderLineRequest;
import com.example.order.dto.response.OrderLineResponse;
import com.example.order.model.Order;
import com.example.order.model.OrderLine;

/**
 * OrderLineMapper Mapper class for converting between OrderLine entities and
 * DTOs.
 */
@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder().id(request.getId()).productId(request.getProductId())
                .order(Order.builder().id(request.getOrderId()).build())
                .quantity(request.getQuantity()).build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine o) {
        return OrderLineResponse.builder().id(o.getId()).productId(o.getProductId())
                .quantity(o.getQuantity()).build();
    }

}
