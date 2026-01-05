package com.example.order.clients.product;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.order.clients.product.dto.request.ProductPurchaseRequest;
import com.example.order.clients.product.dto.response.ProductPurchaseResponse;
import com.example.order.clients.product.dto.response.ProductResponse;

/**
 * ProductClient Feign client for communicating with the Product Service.
 */
@FeignClient(name = "product-service", url = "${application.config.product-url}")
public interface ProductClient {

    @GetMapping("/{id}/check-quantity/{quantity}")
    boolean checkProductQuantity(@PathVariable Long id, @PathVariable double quantity);

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@RequestParam Long id);

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request);
}
