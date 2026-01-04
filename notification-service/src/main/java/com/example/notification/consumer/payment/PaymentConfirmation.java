package com.example.notification.consumer.payment;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentConfirmation {

    private String orderReference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String customerFirstname;
    private String customerLastname;;
    private String customerEmail;

}
