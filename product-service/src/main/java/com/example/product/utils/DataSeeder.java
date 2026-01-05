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
public class DataSeeder {

        @Bean
        CommandLineRunner seedData(CategoryRepository categoryRepository,
                        ProductRepository productRepository) {

                return args -> {
                        // Seed categories if none exist
                        if (categoryRepository.count() == 0) {
                                List<Category> categories = List.of(Category.builder()
                                                .name("Electronics")
                                                .description("Electronic devices, gadgets and accessories")
                                                .build(),
                                                Category.builder().name("Fashion")
                                                                .description("Clothing, shoes and fashion accessories")
                                                                .build(),
                                                Category.builder().name("Home & Kitchen")
                                                                .description("Home appliances and kitchen equipment")
                                                                .build(),
                                                Category.builder().name("Books")
                                                                .description("Books, magazines and educational materials")
                                                                .build(),
                                                Category.builder()
                                                                .name("Sports & Fitness")
                                                                .description("Sports gear, fitness equipment and accessories")
                                                                .build(),
                                                Category.builder().name(
                                                                "Beauty & Personal Care")
                                                                .description("Cosmetics, skincare and personal care products")
                                                                .build(),
                                                Category.builder().name("Toys & Games")
                                                                .description("Toys, board games and entertainment for all ages")
                                                                .build(),
                                                Category.builder().name("Automotive")
                                                                .description("Car accessories and automotive tools")
                                                                .build(),
                                                Category.builder().name("Health")
                                                                .description("Healthcare products and wellness items")
                                                                .build(),
                                                Category.builder().name("Office Supplies")
                                                                .description("Office equipment, stationery and supplies")
                                                                .build());
                                categoryRepository.saveAllAndFlush(categories); // Flush
                                                                                // to
                                                                                // ensure
                                                                                // persistence
                        }

                        // Seed products if none exist
                        if (productRepository.count() == 0) {
                                List<Category> categories = categoryRepository.findAll();
                                if (categories.isEmpty()) {
                                        throw new IllegalStateException(
                                                        "Failed to seed or retrieve categories. Check database connection and entity configuration.");
                                }

                                List<Product> products = List.of(Product.builder()
                                                .name("Wireless Mechanical Keyboard")
                                                .description("Compact 75% wireless mechanical keyboard with hot-swappable switches")
                                                .availableQuantity(120)
                                                .price(BigDecimal.valueOf(189.99))
                                                .category(categories.get(0)).build(),
                                                Product.builder().name(
                                                                "Premium Cotton Hoodie")
                                                                .description("Unisex premium cotton hoodie with modern fit")
                                                                .availableQuantity(80)
                                                                .price(BigDecimal.valueOf(
                                                                                59.90))
                                                                .category(categories
                                                                                .get(1))
                                                                .build(),
                                                Product.builder().name("Air Fryer 5L")
                                                                .description("Digital air fryer with rapid hot air circulation")
                                                                .availableQuantity(45)
                                                                .price(BigDecimal.valueOf(
                                                                                129.50))
                                                                .category(categories
                                                                                .get(2))
                                                                .build(),
                                                Product.builder().name(
                                                                "Clean Architecture")
                                                                .description("A guide to software structure and design by Robert C. Martin")
                                                                .availableQuantity(200)
                                                                .price(BigDecimal.valueOf(
                                                                                39.99))
                                                                .category(categories
                                                                                .get(3))
                                                                .build(),
                                                Product.builder().name(
                                                                "Adjustable Dumbbell Set")
                                                                .description("20kg adjustable dumbbell set for home workouts")
                                                                .availableQuantity(60)
                                                                .price(BigDecimal.valueOf(
                                                                                149.00))
                                                                .category(categories
                                                                                .get(4))
                                                                .build(),
                                                Product.builder().name(
                                                                "Vitamin C Face Serum")
                                                                .description("Brightening face serum with hyaluronic acid")
                                                                .availableQuantity(150)
                                                                .price(BigDecimal.valueOf(
                                                                                24.99))
                                                                .category(categories
                                                                                .get(5))
                                                                .build(),
                                                Product.builder().name(
                                                                "Educational Building Blocks")
                                                                .description("STEM building blocks set for kids aged 6+")
                                                                .availableQuantity(100)
                                                                .price(BigDecimal.valueOf(
                                                                                34.90))
                                                                .category(categories
                                                                                .get(6))
                                                                .build(),
                                                Product.builder().name("Car Phone Mount")
                                                                .description("Magnetic dashboard car phone holder")
                                                                .availableQuantity(130)
                                                                .price(BigDecimal.valueOf(
                                                                                19.99))
                                                                .category(categories
                                                                                .get(7))
                                                                .build(),
                                                Product.builder().name(
                                                                "Digital Blood Pressure Monitor")
                                                                .description("Automatic upper arm blood pressure monitor")
                                                                .availableQuantity(70)
                                                                .price(BigDecimal.valueOf(
                                                                                54.99))
                                                                .category(categories
                                                                                .get(8))
                                                                .build(),
                                                Product.builder().name(
                                                                "Ergonomic Office Chair")
                                                                .description("Adjustable ergonomic chair with lumbar support")
                                                                .availableQuantity(35)
                                                                .price(BigDecimal.valueOf(
                                                                                249.00))
                                                                .category(categories
                                                                                .get(9))
                                                                .build());

                                productRepository.saveAll(products);
                        }
                };
        }
}