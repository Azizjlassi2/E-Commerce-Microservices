package com.example.order.service;

import org.springframework.stereotype.Service;

import com.example.order.clients.customer.CustomerClient;
import com.example.order.clients.product.ProductClient;
import com.example.order.dto.request.OrderLineRequest;
import com.example.order.dto.request.OrderRequest;
import com.example.order.dto.request.PurchaseRequest;
import com.example.order.exceptions.CustomerNotFoundException;
import com.example.order.exceptions.ProductQuantityException;
import com.example.order.mapper.OrderMapper;
import com.example.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;

    public Long createOrder(OrderRequest request) {

        // check the customer
        var customer = this.customerClient.getCustomerById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Cannot create order :: No Customer exists with the provided ID:: "
                                + request.getCustomerId()));

        // check the products quantity -> product-ms
        for (var item : request.getProducts()) {
            if (!this.productClient.checkProductQuantity(item.getProductId(),
                    item.getQuantity())) {

                throw new ProductQuantityException(
                        "Cannot create order :: Product with the provided ID:: "
                                + item.getProductId()
                                + " does not have the provided QUANTITY::"
                                + item.getQuantity());
            }
        }

        // persist order
        var order = this.orderRepository.save(orderMapper.toOrder(request));

        // persist order lines
        for (PurchaseRequest purchaseRequest : request.getProducts()) {
            orderLineService.createOrderLine(OrderLineRequest.builder().id(null)
                    .orderId(order.getId()).productId(purchaseRequest.getProductId())
                    .quantity(purchaseRequest.getQuantity()).build());

        }

        // TODO start payment process -> payment-ms

        // send the order confirmation -> notification-ms

        return null;

    }

}
