package com.example.order.producer;

import java.math.BigDecimal;
import java.util.List;

import com.example.order.clients.customer.dto.response.CustomerResponse;
import com.example.order.clients.product.dto.response.ProductResponse;
import com.example.order.model.PaymentMethod;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderConfirmation {

    String orderReference;

    BigDecimal totalAmount;

    PaymentMethod paymentMethod;

    CustomerResponse customerResponse;

    List<ProductResponse> products;

}
