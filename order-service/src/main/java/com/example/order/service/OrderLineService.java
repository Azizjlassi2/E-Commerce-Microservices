package com.example.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.order.dto.request.OrderLineRequest;
import com.example.order.dto.response.OrderLineResponse;
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

    public List<OrderLineResponse> findAllByOrderId(Long id) {
        return orderLineRepository.findAllByOrderId(id).stream()
                .map(orderLineMapper::toOrderLineResponse).collect(Collectors.toList());

    }

}
