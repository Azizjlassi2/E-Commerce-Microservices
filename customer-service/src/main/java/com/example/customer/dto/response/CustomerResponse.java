package com.example.customer.dto.response;

import com.example.customer.model.Address;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    private long id;

    private String firstname;

    private String lastname;

    private String email;

    private Address address;

}
