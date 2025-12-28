package com.example.customer.exceptions;

import java.util.Map;

public record ErrorResponse(

        Map<String, String> errors) {

}
