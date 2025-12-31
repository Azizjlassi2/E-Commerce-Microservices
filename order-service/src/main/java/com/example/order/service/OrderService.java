package com.example.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.order.clients.customer.CustomerClient;
import com.example.order.clients.product.ProductClient;
import com.example.order.clients.product.dto.response.ProductResponse;
import com.example.order.dto.request.OrderLineRequest;
import com.example.order.dto.request.OrderRequest;
import com.example.order.dto.request.PurchaseRequest;
import com.example.order.dto.response.OrderResponse;
import com.example.order.exceptions.CustomerNotFoundException;
import com.example.order.exceptions.ProductQuantityException;
import com.example.order.mapper.OrderMapper;
import com.example.order.producer.OrderConfirmation;
import com.example.order.producer.OrderProducer;
import com.example.order.repository.OrderRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    // TODO : implement the purchase logic in the product service :: substruct the
    // quantity for each product ...

    public Long createOrder(OrderRequest request) {

        // check the customer
        var customer = this.customerClient.getCustomerById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Cannot create order :: No Customer exists with the provided ID:: "
                                + request.getCustomerId()));

        // check the products quantity -> product-ms
        List<ProductResponse> products = new ArrayList<>();

        for (var item : request.getProducts()) {
            if (!this.productClient.checkProductQuantity(item.getProductId(),
                    item.getQuantity())) {

                throw new ProductQuantityException(
                        "Cannot create order :: Product with the provided ID:: "
                                + item.getProductId()
                                + " does not have the provided QUANTITY::"
                                + item.getQuantity());
            }
            products.add(
                    this.productClient.findProductById(item.getProductId()).getBody());
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
        orderProducer.sendOrderConfirmation(OrderConfirmation.builder()
                .customerResponse(customer).orderReference(request.getReference())
                .totalAmount(request.getAmount()).products(products)
                .paymentMethod(request.getPaymentMethod()).build());

        return order.getId();

    }

    public List<OrderResponse> findAll() {

        return orderRepository.findAll().stream().map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse findOrderById(Long id) {
        return orderRepository.findById(id).map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No order found with the provided ID:: %d", id)));
    }
}
