package com.example.customer.mapper;

import org.springframework.stereotype.Service;

import com.example.customer.dto.request.CustomerRequest;
import com.example.customer.dto.response.CustomerResponse;
import com.example.customer.model.Customer;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {

        if (request == null)
            return null;

        return Customer.builder().firstname(request.getFirstname())
                .lastname(request.getLastname()).email(request.getEmail())
                .address(request.getAddress()).build();

    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        if (customer == null) {
            return null;
        }

        return CustomerResponse.builder().id(customer.getId())
                .firstname(customer.getFirstname()).lastname(customer.getLastname())
                .email(customer.getEmail()).address(customer.getAddress()).build();
    }

}
