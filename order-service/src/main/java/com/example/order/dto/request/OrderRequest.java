package com.example.order.dto.request;

import java.math.BigDecimal;
import java.util.List;

import com.example.order.model.PaymentMethod;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    @NotBlank(message = "Payment Method should be precised")
    private PaymentMethod paymentMethod;

    @NotEmpty(message = "Customer should be present")
    @NotBlank(message = "Customer should be present")
    private Long customerId;

    @NotEmpty(message = "You should at least purchase one product")
    private List<PurchaseRequest> products;

}
