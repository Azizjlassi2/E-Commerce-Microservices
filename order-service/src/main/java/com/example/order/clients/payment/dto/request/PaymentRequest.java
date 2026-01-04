package com.example.order.clients.payment.dto.request;

import java.math.BigDecimal;

import com.example.order.clients.customer.dto.response.CustomerResponse;
import com.example.order.model.PaymentMethod;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {

    Long id;
    BigDecimal amount;
    PaymentMethod paymentMethod;
    Long orderId;
    String orderReference;
    CustomerResponse customer;

}
