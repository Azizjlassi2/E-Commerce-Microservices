package com.example.order.clients.customer.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    Long id;
    String firstname;
    String lastname;
    String email;

}
