package com.example.order.dto.response;

import java.math.BigDecimal;

import com.example.order.model.PaymentMethod;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {

    Long id;
    String reference;

    BigDecimal amount;

    PaymentMethod paymentMethod;

    Long customerId;

}
