package com.example.notification.converters;

import com.example.notification.consumer.order.OrderConfirmation;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * OrderConfirmationConverter Converts OrderConfirmation objects to JSON strings
 * for database storage and vice versa.
 */
@Converter(autoApply = false)
public class OrderConfirmationConverter
        implements AttributeConverter<OrderConfirmation, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(OrderConfirmation attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Error converting OrderConfirmation to JSON", e);
        }
    }

    @Override
    public OrderConfirmation convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, OrderConfirmation.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Error converting JSON to OrderConfirmation", e);
        }
    }
}