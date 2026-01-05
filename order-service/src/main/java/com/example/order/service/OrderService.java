package com.example.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.order.clients.customer.CustomerClient;
import com.example.order.clients.customer.dto.response.CustomerResponse;
import com.example.order.clients.payment.PaymentClient;
import com.example.order.clients.payment.dto.request.PaymentRequest;
import com.example.order.clients.product.ProductClient;
import com.example.order.clients.product.dto.request.ProductPurchaseRequest;
import com.example.order.clients.product.dto.response.ProductPurchaseResponse;
import com.example.order.clients.product.dto.response.ProductResponse;
import com.example.order.dto.request.OrderLineRequest;
import com.example.order.dto.request.OrderRequest;
import com.example.order.dto.request.PurchaseRequest;
import com.example.order.dto.response.OrderResponse;
import com.example.order.exceptions.CustomerNotFoundException;
import com.example.order.mapper.OrderMapper;
import com.example.order.producer.OrderConfirmation;
import com.example.order.producer.OrderProducer;
import com.example.order.repository.OrderRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * OrderService Service class for managing orders. Handles order creation,
 * retrieval, and integration with customer, product, and payment services.
 */
@Service
@RequiredArgsConstructor
public class OrderService {

        private final CustomerClient customerClient;
        private final ProductClient productClient;
        private final PaymentClient paymentClient;

        private final OrderRepository orderRepository;
        private final OrderMapper orderMapper;
        private final OrderLineService orderLineService;
        private final OrderProducer orderProducer;

        public Long createOrder(OrderRequest request) {

                // check the customer
                var customer = this.customerClient
                                .getCustomerById(request.getCustomerId())
                                .orElseThrow(() -> new CustomerNotFoundException(
                                                "Cannot create order :: No Customer exists with the provided ID:: "
                                                                + request.getCustomerId()));

                // puchase the products -> product-ms

                List<ProductPurchaseRequest> purchaseRequests = new ArrayList<>();
                for (PurchaseRequest p : request.getProducts()) {
                        purchaseRequests.add(ProductPurchaseRequest.builder()
                                        .productId(p.getProductId())
                                        .quantity(p.getQuantity()).build());

                }
                // collect purchased products info
                List<ProductResponse> products = new ArrayList<>();
                List<ProductPurchaseResponse> purchaseProductsResponse = this.productClient
                                .purchaseProducts(purchaseRequests).getBody();
                purchaseProductsResponse.forEach(p -> {
                        products.add(ProductResponse.builder().id(p.getProductId())
                                        .availableQuality(p.getQuantity())
                                        .name(p.getName()).price(p.getPrice())
                                        .description(p.getDescription())
                                        .categoryId(p.getCategoryId()).build());
                });

                // persist order
                var order = this.orderRepository.save(orderMapper.toOrder(request));

                // persist order lines
                for (PurchaseRequest purchaseRequest : request.getProducts()) {
                        orderLineService.createOrderLine(OrderLineRequest.builder()
                                        .id(null).orderId(order.getId())
                                        .productId(purchaseRequest.getProductId())
                                        .quantity(purchaseRequest.getQuantity()).build());

                }

                // start payment process -> payment-ms

                paymentClient.requestOrderPayment(PaymentRequest.builder()
                                .orderId(order.getId())
                                .orderReference(order.getReference())
                                .paymentMethod(order.getPaymentMethod())
                                .amount(order.getAmount())
                                .customer(CustomerResponse.builder().id(customer.getId())
                                                .email(customer.getEmail())
                                                .firstname(customer.getFirstname())
                                                .lastname(customer.getLastname()).build())
                                .build());

                // send the order confirmation -> notification-ms
                orderProducer.sendOrderConfirmation(OrderConfirmation.builder()
                                .customerResponse(customer)
                                .orderReference(request.getReference())
                                .totalAmount(request.getAmount()).products(products)
                                .paymentMethod(request.getPaymentMethod()).build());

                return order.getId();

        }

        public List<OrderResponse> findAll() {

                return orderRepository.findAll().stream()
                                .map(orderMapper::toOrderResponse)
                                .collect(Collectors.toList());
        }

        public OrderResponse findOrderById(Long id) {
                return orderRepository.findById(id).map(orderMapper::toOrderResponse)
                                .orElseThrow(() -> new EntityNotFoundException(String
                                                .format("No order found with the provided ID:: %d",
                                                                id)));
        }
}
