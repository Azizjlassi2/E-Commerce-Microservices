package com.example.notification.consumer.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;

}
