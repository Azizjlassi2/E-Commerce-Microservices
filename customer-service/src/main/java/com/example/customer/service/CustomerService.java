package com.example.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.customer.dto.request.CustomerRequest;
import com.example.customer.dto.response.CustomerResponse;
import com.example.customer.exceptions.CustomerNotFoundException;
import com.example.customer.mapper.CustomerMapper;
import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public List<CustomerResponse> findCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::toCustomerResponse)
                .collect(Collectors.toList());

    }

    public CustomerResponse findCustomerById(Long id) {
        return customerMapper.toCustomerResponse(customerRepository.findById(id).get());

    }

    public Long createCustomer(CustomerRequest request) {
        return customerRepository.save(customerMapper.toCustomer(request)).getId();

    }

    public void updateCustomer(CustomerRequest request) {

        var customer = customerRepository.findById(request.getId()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Cannot update customer :: No Customer found with the provided ID :: %s",
                        request.getId())));

        mergeCustomer(customer, request);
        customerRepository.save(customer);

    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.getFirstname())) {
            customer.setFirstname(request.getFirstname());
        }

        if (StringUtils.isNotBlank(request.getLastname())) {
            customer.setLastname(request.getLastname());
        }

        if (StringUtils.isNotBlank(request.getEmail())) {
            customer.setEmail(request.getEmail());
        }
        if (request.getAddress() != null) {
            customer.setAddress(request.getAddress());
        }
    }

    public void deleteCustomerById(Long id) {
        var customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(
                    String.format("Cannot delete customer :: No Customer found with the provided ID :: %s",
                            id));
        }

        customerRepository.deleteById(id);

    }

}
