package com.example.product.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductInsufficientQuantityException extends RuntimeException {

    private final String msg;

}
