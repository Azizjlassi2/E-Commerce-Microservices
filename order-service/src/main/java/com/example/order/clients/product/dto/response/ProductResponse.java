package com.example.order.clients.product.dto.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Long id;

    private String name;
    private String description;
    private double availableQuality;
    private BigDecimal price;
    private Long categoryId;

}
