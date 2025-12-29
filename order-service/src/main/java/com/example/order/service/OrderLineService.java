package com.example.order.service;

import org.springframework.stereotype.Service;

import com.example.order.dto.request.OrderLineRequest;
import com.example.order.mapper.OrderLineMapper;
import com.example.order.repository.OrderLineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineMapper orderLineMapper;
    private final OrderLineRepository orderLineRepository;

    public Long createOrderLine(OrderLineRequest request) {
        return orderLineRepository.save(orderLineMapper.toOrderLine(request)).getId();
    }

}
