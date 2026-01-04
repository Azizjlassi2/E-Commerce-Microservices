package com.example.payment.mapper;

import org.springframework.stereotype.Service;

import com.example.payment.dto.request.PaymentRequest;
import com.example.payment.model.Payment;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest req) {

        if (req == null) {
            return null;
        }

        return Payment.builder().id(req.getId()).amount(req.getAmount())
                .orderId(req.getOrderId()).paymentMethod(req.getPaymentMethod()).build();
    }

}
