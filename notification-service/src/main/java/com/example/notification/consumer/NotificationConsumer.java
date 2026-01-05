package com.example.notification.consumer;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.notification.consumer.order.OrderConfirmation;
import com.example.notification.consumer.payment.PaymentConfirmation;
import com.example.notification.model.Notification;
import com.example.notification.model.NotificationType;
import com.example.notification.repository.NotificationRepository;
import com.example.notification.service.EmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * NotificationConsumer Consumes messages from Kafka topics related to
 * notifications. Handles payment and order confirmation notifications. Saves
 * notifications to the database and triggers email sending.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
        private final NotificationRepository notificationRepository;
        private final EmailService emailService;

        @KafkaListener(topics = "payment-topic")
        public void consumePaymentSuccessNotification(PaymentConfirmation conf)
                        throws MessagingException {
                log.info("Consuming the message from payment-topic Topic:: ${} ", conf);

                notificationRepository.save(Notification.builder()
                                .type(NotificationType.PAYMENT_CONFIRMATION)
                                .notificationDate(LocalDateTime.now())
                                .paymentConfirmation(conf).build());

                var customerName = conf.getCustomerFirstname() + " "
                                + conf.getCustomerLastname();
                emailService.sendPaymentSuccessEmail(conf.getCustomerEmail(),
                                customerName, conf.getAmount(), conf.getOrderReference());

        }

        @KafkaListener(topics = "order-topic")
        public void consumeOrderConfirmationNotification(OrderConfirmation conf)
                        throws MessagingException {
                log.info("Consuming the message from order-topic Topic:: ${} ", conf);

                notificationRepository.save(Notification.builder()
                                .type(NotificationType.ORDER_CONFIRMATION)
                                .notificationDate(LocalDateTime.now())
                                .orderConfirmation(conf).build());

                var customerName = conf.getCustomer().getFirstname() + " "
                                + conf.getCustomer().getLastname();

                emailService.sendOrderConfirmationEmail(conf.getCustomer().getEmail(),
                                customerName, conf.getAmount(), conf.getOrderReference(),
                                conf.getProducts());

        }

}
