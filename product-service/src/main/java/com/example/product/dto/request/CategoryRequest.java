package com.example.product.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {

    private Long id;

    private String name;
    private String description;

}
