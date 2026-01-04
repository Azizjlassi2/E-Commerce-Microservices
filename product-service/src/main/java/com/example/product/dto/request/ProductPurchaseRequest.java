package com.example.product.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductPurchaseRequest {

    @NotNull(message = "Product ID is mandatory")
    Long productId;
    @NotNull(message = "Product Quantity is mandatory")
    double quantity;

}
