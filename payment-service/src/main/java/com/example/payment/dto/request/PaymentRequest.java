package com.example.payment.dto.request;

import java.math.BigDecimal;

import com.example.payment.model.PaymentMethod;

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
    Customer customer;

}
