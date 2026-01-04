package com.example.order.clients.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.order.clients.payment.dto.request.PaymentRequest;

@FeignClient(name = "payment-service", url = "${application.config.payment-url}")
public interface PaymentClient {

    @PostMapping
    public Long requestOrderPayment(@RequestBody PaymentRequest request);

}
