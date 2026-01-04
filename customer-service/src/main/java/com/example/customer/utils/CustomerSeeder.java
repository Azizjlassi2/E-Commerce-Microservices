package com.example.customer.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.customer.model.Address;
import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;

@Configuration
public class CustomerSeeder {

    @Bean
    CommandLineRunner seedCustomers(CustomerRepository customerRepository) {
        return args -> {

            if (customerRepository.count() > 0)
                return;

            List<Customer> customers = List.of(

                    Customer.builder().firstname("Aziz").lastname("Jlassi")
                            .email("aziz.jlassi@example.com")
                            .address(Address.builder().street("12 Rue des Entrepreneurs")
                                    .city("Tunis").zipcode("1001").build())
                            .build(),

                    Customer.builder().firstname("Sara").lastname("Ben Ali")
                            .email("sara.benali@example.com")
                            .address(Address.builder().street("45 Avenue Habib Bourguiba")
                                    .city("Sousse").zipcode("4000").build())
                            .build(),

                    Customer.builder().firstname("Youssef").lastname("Trabelsi")
                            .email("youssef.trabelsi@example.com")
                            .address(Address.builder().street("8 Rue Ibn Khaldoun")
                                    .city("Sfax").zipcode("3000").build())
                            .build(),

                    Customer.builder().firstname("Lina").lastname("Mansouri")
                            .email("lina.mansouri@example.com")
                            .address(Address.builder().street("21 Rue El Manar")
                                    .city("Ariana").zipcode("2080").build())
                            .build(),

                    Customer.builder().firstname("Karim").lastname("Zouari")
                            .email("karim.zouari@example.com")
                            .address(Address.builder().street("5 Rue de la Liberté")
                                    .city("Bizerte").zipcode("7000").build())
                            .build());

            customerRepository.saveAll(customers);
        };
    }
}