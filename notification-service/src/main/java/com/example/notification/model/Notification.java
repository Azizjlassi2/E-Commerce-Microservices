package com.example.notification.model;

import java.time.LocalDateTime;

import com.example.notification.consumer.order.OrderConfirmation;
import com.example.notification.consumer.payment.PaymentConfirmation;
import com.example.notification.converters.OrderConfirmationConverter;
import com.example.notification.converters.PaymentConfirmationConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private LocalDateTime notificationDate;
    @Lob
    @Convert(converter = OrderConfirmationConverter.class)
    private OrderConfirmation orderConfirmation;
    @Lob
    @Convert(converter = PaymentConfirmationConverter.class)
    private PaymentConfirmation paymentConfirmation;

}