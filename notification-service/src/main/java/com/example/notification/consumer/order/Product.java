package com.example.notification.consumer.order;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;

}
