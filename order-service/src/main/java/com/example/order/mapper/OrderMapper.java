package com.example.order.mapper;

import org.springframework.stereotype.Service;

import com.example.order.dto.request.OrderRequest;
import com.example.order.model.Order;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
        return Order.builder().id(request.getId()).customerId(request.getCustomerId())
                .reference(request.getReference()).amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod()).build();
    }

}
