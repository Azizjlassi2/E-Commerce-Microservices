package com.example.order.clients.product.dto.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductPurchaseResponse {
    Long productId;
    String name;
    String description;
    BigDecimal price;
    double quantity;
    Long categoryId;

}
