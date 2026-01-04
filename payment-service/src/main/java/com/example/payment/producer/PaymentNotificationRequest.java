package com.example.payment.producer;

import java.math.BigDecimal;

import com.example.payment.model.PaymentMethod;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentNotificationRequest {

    String orderReference;
    BigDecimal amount;
    PaymentMethod paymentMethod;
    String customerFirstName;
    String customerLastName;
    String customerEmail;
}
