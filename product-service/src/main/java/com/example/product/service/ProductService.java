package com.example.product.service;

import java.util.List;
import java.util.stream.Collectors;

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
                        "Cannot update product :: No Product found with the provided ID :: %s",
                        id)));

    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse)
                .collect(Collectors.toList());

    }

    public Boolean checkProductQuantity(Long id, double quantity) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format(
                        "Cannot check quantity :: No Product found with the provided ID :: %s",
                        id)));

        return product.getAvailableQuantity() >= quantity;
    }

}
