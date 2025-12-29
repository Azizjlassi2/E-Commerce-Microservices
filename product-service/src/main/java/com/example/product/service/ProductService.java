package com.example.product.service;

import org.springframework.stereotype.Service;

import com.example.product.dto.request.ProductRequest;
import com.example.product.dto.response.ProductResponse;
import com.example.product.exceptions.ProductNotFoundException;
import com.example.product.mapper.ProductMapper;
import com.example.product.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Long createProduct(ProductRequest request) {
        return productRepository.save(productMapper.toProduct(request)).getId();

    }

    public ProductResponse findProductById(Long id) {
        return productRepository.findById(id).map(productMapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException(String.format(
                        "Cannot update customer :: No Customer found with the provided ID :: %s",
                        id)));

    }

}
