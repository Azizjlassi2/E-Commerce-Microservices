package com.example.order.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderLineResponse {

    Long id;
    Long productId;
    double quantity;

}
