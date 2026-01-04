package com.example.payment.dto.request;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Validated
@Data
public class Customer {
    Long id;
    @NotNull(message = "Firstname is required")
    String firstname;

    @NotNull(message = "Lastname is required")
    String lastname;

    @NotNull(message = "Email is required")
    @Email(message = "Email is not correctly formatted")
    String email;
}
