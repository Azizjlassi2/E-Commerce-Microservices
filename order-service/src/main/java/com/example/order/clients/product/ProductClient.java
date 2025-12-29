package com.example.order.clients.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "${application.config.product-url}")
public interface ProductClient {

    @GetMapping("/{id}/check-quantity/{quantity}")
    boolean checkProductQuantity(@PathVariable Long id, @PathVariable double quantity);

}
