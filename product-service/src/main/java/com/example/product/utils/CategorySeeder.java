package com.example.product.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.product.model.Category;
import com.example.product.repository.CategoryRepository;

@Configuration
public class CategorySeeder {

        @Bean
        CommandLineRunner initCategories(CategoryRepository categoryRepository) {
                return args -> {

                        if (categoryRepository.count() > 0)
                                return;

                        List<Category> categories = List.of(Category.builder()
                                        .name("Electronics")
                                        .description("Electronic devices, gadgets and accessories")
                                        .build(),

                                        Category.builder().name("Fashion").description(
                                                        "Clothing, shoes and fashion accessories")
                                                        .build(),

                                        Category.builder().name("Home & Kitchen")
                                                        .description("Home appliances and kitchen equipment")
                                                        .build(),

                                        Category.builder().name("Books").description(
                                                        "Books, magazines and educational materials")
                                                        .build(),

                                        Category.builder().name("Sports & Fitness")
                                                        .description("Sports gear, fitness equipment and accessories")
                                                        .build(),

                                        Category.builder().name("Beauty & Personal Care")
                                                        .description("Cosmetics, skincare and personal care products")
                                                        .build(),

                                        Category.builder().name("Toys & Games")
                                                        .description("Toys, board games and entertainment for all ages")
                                                        .build(),

                                        Category.builder().name("Automotive").description(
                                                        "Car accessories and automotive tools")
                                                        .build(),

                                        Category.builder().name("Health").description(
                                                        "Healthcare products and wellness items")
                                                        .build(),

                                        Category.builder().name("Office Supplies")
                                                        .description("Office equipment, stationery and supplies")
                                                        .build());

                        categoryRepository.saveAll(categories);
                };
        }
}
