package com.example.product.utils;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.product.model.Category;
import com.example.product.model.Product;
import com.example.product.repository.CategoryRepository;
import com.example.product.repository.ProductRepository;

@Configuration
public class ProductSeeder {

    @Bean
    CommandLineRunner seedProducts(ProductRepository productRepository,
            CategoryRepository categoryRepository) {

        return args -> {

            if (productRepository.count() > 0)
                return;

            List<Category> categories = categoryRepository.findAll();
            if (categories.size() < 10) {
                throw new IllegalStateException(
                        "Categories must be seeded before products");
            }

            List<Product> products = List.of(
                    Product.builder().name("Wireless Mechanical Keyboard").description(
                            "Compact 75% wireless mechanical keyboard with hot-swappable switches")
                            .availableQuantity(120).price(BigDecimal.valueOf(189.99))
                            .category(categories.get(0)) // Electronics
                            .build(),

                    Product.builder().name("Premium Cotton Hoodie")
                            .description("Unisex premium cotton hoodie with modern fit")
                            .availableQuantity(80).price(BigDecimal.valueOf(59.90))
                            .category(categories.get(1)) // Fashion
                            .build(),

                    Product.builder().name("Air Fryer 5L")
                            .description(
                                    "Digital air fryer with rapid hot air circulation")
                            .availableQuantity(45).price(BigDecimal.valueOf(129.50))
                            .category(categories.get(2)) // Home & Kitchen
                            .build(),

                    Product.builder().name("Clean Architecture").description(
                            "A guide to software structure and design by Robert C. Martin")
                            .availableQuantity(200).price(BigDecimal.valueOf(39.99))
                            .category(categories.get(3)) // Books
                            .build(),

                    Product.builder().name("Adjustable Dumbbell Set")
                            .description("20kg adjustable dumbbell set for home workouts")
                            .availableQuantity(60).price(BigDecimal.valueOf(149.00))
                            .category(categories.get(4)) // Sports & Fitness
                            .build(),

                    Product.builder().name("Vitamin C Face Serum")
                            .description("Brightening face serum with hyaluronic acid")
                            .availableQuantity(150).price(BigDecimal.valueOf(24.99))
                            .category(categories.get(5)) // Beauty
                            .build(),

                    Product.builder().name("Educational Building Blocks")
                            .description("STEM building blocks set for kids aged 6+")
                            .availableQuantity(100).price(BigDecimal.valueOf(34.90))
                            .category(categories.get(6)) // Toys
                            .build(),

                    Product.builder().name("Car Phone Mount")
                            .description("Magnetic dashboard car phone holder")
                            .availableQuantity(130).price(BigDecimal.valueOf(19.99))
                            .category(categories.get(7)) // Automotive
                            .build(),

                    Product.builder().name("Digital Blood Pressure Monitor")
                            .description("Automatic upper arm blood pressure monitor")
                            .availableQuantity(70).price(BigDecimal.valueOf(54.99))
                            .category(categories.get(8)) // Health
                            .build(),

                    Product.builder().name("Ergonomic Office Chair")
                            .description("Adjustable ergonomic chair with lumbar support")
                            .availableQuantity(35).price(BigDecimal.valueOf(249.00))
                            .category(categories.get(9)) // Office Supplies
                            .build());

            productRepository.saveAll(products);
        };
    }
}