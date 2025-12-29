package com.example.order.mapper;

import org.springframework.stereotype.Service;

import com.example.order.dto.request.OrderLineRequest;
import com.example.order.model.Order;
import com.example.order.model.OrderLine;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder().id(request.getId()).productId(request.getProductId())
                .order(Order.builder().id(request.getOrderId()).build())
                .quantity(request.getQuantity()).build();
    }

}
