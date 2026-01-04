package com.example.notification.consumer.order;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderConfirmation {

    private String orderReference;
    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private Customer customer;

    private List<Product> products;
}
