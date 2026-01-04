package com.example.order.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.order.model.Order;
import com.example.order.model.OrderLine;
import com.example.order.model.PaymentMethod;
import com.example.order.repository.OrderLineRepository;
import com.example.order.repository.OrderRepository;

@Configuration
public class OrderSeeder {

    @Bean
    CommandLineRunner seedOrders(OrderRepository orderRepository,
            OrderLineRepository orderLineRepository) {

        return args -> {

            if (orderRepository.count() > 0)
                return;

            Random random = new Random();

            List<Order> orders = List.of(
                    Order.builder().reference("ORD-1001")
                            .amount(BigDecimal.valueOf(189.99))
                            .paymentMethod(PaymentMethod.PAYPAL).customerId(1L).build(),

                    Order.builder().reference("ORD-1002")
                            .amount(BigDecimal.valueOf(249.00))
                            .paymentMethod(PaymentMethod.CREDIT_CARD).customerId(2L)
                            .build(),

                    Order.builder().reference("ORD-1003")
                            .amount(BigDecimal.valueOf(129.50))
                            .paymentMethod(PaymentMethod.PAYPAL).customerId(3L).build(),

                    Order.builder().reference("ORD-1004")
                            .amount(BigDecimal.valueOf(54.99))
                            .paymentMethod(PaymentMethod.CREDIT_CARD).customerId(4L)
                            .build(),

                    Order.builder().reference("ORD-1005")
                            .amount(BigDecimal.valueOf(349.99))
                            .paymentMethod(PaymentMethod.PAYPAL).customerId(5L).build());

            orderRepository.saveAll(orders);

            // Mock order lines
            for (Order order : orders) {

                int linesCount = 1 + random.nextInt(3); // 1 à 3 lignes par commande
                for (int i = 0; i < linesCount; i++) {

                    // productId mock (1 à 10)
                    long productId = 1 + random.nextInt(10);

                    OrderLine line = OrderLine.builder().order(order).productId(productId)
                            .quantity(1 + random.nextInt(5)) // 1 à 5 unités
                            .build();

                    orderLineRepository.save(line);
                }
            }
        };
    }
}