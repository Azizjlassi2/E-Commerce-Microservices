package com.example.order.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderLineRequest {

    private Long id;
    private Long orderId;
    private Long productId;
    private double quantity;

}
