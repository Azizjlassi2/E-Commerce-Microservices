package com.example.product.mapper;

import org.springframework.stereotype.Service;

import com.example.product.dto.request.ProductRequest;
import com.example.product.dto.response.ProductPurchaseResponse;
import com.example.product.dto.response.ProductResponse;
import com.example.product.model.Category;
import com.example.product.model.Product;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        if (request == null) {
            return null;

        }
        return Product.builder().id(request.getId()).name(request.getName())
                .description(request.getDescription())
                .availableQuantity(request.getAvailableQuantity())
                .price(request.getPrice())
                .category(Category.builder().id(request.getCategoryId()).build()).build();

    }

    public ProductResponse toProductResponse(Product product) {
        if (product == null) {
            return null;
        }
        return ProductResponse.builder().id(product.getId()).name(product.getName())
                .description(product.getDescription())
                .availableQuality(product.getAvailableQuantity())
                .price(product.getPrice()).categoryId(product.getCategory().getId())
                .build();

    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product) {
        if (product == null) {
            return null;
        }
        return ProductPurchaseResponse.builder().productId(product.getId())
                .name(product.getName()).description(product.getDescription())
                .price(product.getPrice()).quantity(product.getAvailableQuantity())
                .categoryId(product.getCategory().getId()).build();
    }

}
