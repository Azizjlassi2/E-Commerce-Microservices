package com.example.product.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;
    @NotBlank(message = "Product description is required")
    private String description;

    @NotBlank(message = "Product avalaible quantity is required")
    @Positive(message = "Product avalaible quantity should be positive")
    private double availableQuantity;

    @NotBlank(message = "Product price is required")
    @Positive(message = "Product price should be positive")
    private BigDecimal price;

    @NotBlank(message = "Product category is required")
    private Long categoryId;

}
