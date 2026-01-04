package com.example.product.dto.request;

import java.math.BigDecimal;

import org.springframework.data.annotation.ReadOnlyProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    @ReadOnlyProperty
    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;
    @NotBlank(message = "Product description is required")
    private String description;

    @NotNull(message = "Product available quantity is required")
    @Positive(message = "Product avalaible quantity should be positive")
    private double availableQuantity;

    @NotNull(message = "Product price is required")
    @Positive(message = "Product price should be positive")
    private BigDecimal price;

    @NotNull(message = "Product category is required")
    private Long categoryId;

}
