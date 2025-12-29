package com.example.order.clients.customer;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.order.clients.customer.dto.response.CustomerResponse;

@FeignClient(name = "customer-service", url = "${application.config.customer-url}")
public interface CustomerClient {

    @GetMapping("/{id}")
    Optional<CustomerResponse> getCustomerById(@PathVariable Long id);
}
