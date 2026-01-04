package com.example.order.dto.request;

import java.math.BigDecimal;
import java.util.List;

import com.example.order.model.PaymentMethod;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequest {

    private Long id;

    private String reference;

    @Positive(message = "Order amount should be positive")
    private BigDecimal amount;

    @NotNull(message = "Payment Method should be precised")

    private PaymentMethod paymentMethod;

    @NotNull(message = "Customer should be present")
    private Long customerId;

    @NotEmpty(message = "You should at least purchase one product")
    private List<PurchaseRequest> products;

}
