package com.example.payment.service;

import org.springframework.stereotype.Service;

import com.example.payment.dto.request.PaymentRequest;
import com.example.payment.mapper.PaymentMapper;
import com.example.payment.producer.NotificationProducer;
import com.example.payment.producer.PaymentNotificationRequest;
import com.example.payment.repository.PaymentRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    private final NotificationProducer notificationProducer;

    public Long createPayment(PaymentRequest req) {
        var payment = paymentRepository.save(paymentMapper.toPayment(req));

        notificationProducer.sendNotification(PaymentNotificationRequest.builder()
                .amount(req.getAmount()).customerEmail(req.getCustomer().getEmail())
                .customerFirstName(req.getCustomer().getFirstname())
                .customerLastName(req.getCustomer().getLastname())
                .orderReference(req.getOrderReference())
                .paymentMethod(req.getPaymentMethod()).build());

        return payment.getId();
    }

}
