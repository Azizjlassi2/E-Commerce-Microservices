package com.example.order.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductQuantityException extends RuntimeException {

    private final String msg;

}
