package com.example.order.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseRequest {

    @NotNull(message = "Product ID is mandatory")
    Long productId;

    @Positive(message = "Quantity should be positive")
    double quantity;

}
