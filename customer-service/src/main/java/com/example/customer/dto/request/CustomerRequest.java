package com.example.customer.dto.request;

import com.example.customer.model.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRequest {

    private long id;

    @NotNull(message = "Customer firstname is required")
    private String firstname;

    @NotNull(message = "Customer lastname is required")
    private String lastname;

    @NotNull(message = "Customer email is required")
    @Email(message = "Customer email is not valid")
    private String email;

    private Address address;

}
