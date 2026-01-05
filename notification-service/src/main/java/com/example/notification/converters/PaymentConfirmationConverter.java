package com.example.notification.converters;

import com.example.notification.consumer.payment.PaymentConfirmation;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * PaymentConfirmationConverter Converts PaymentConfirmation objects to JSON
 * strings for database storage and vice versa.
 */
@Converter(autoApply = false)
public class PaymentConfirmationConverter
        implements AttributeConverter<PaymentConfirmation, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(PaymentConfirmation attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Error converting PaymentConfirmation to JSON", e);
        }
    }

    @Override
    public PaymentConfirmation convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, PaymentConfirmation.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Error converting JSON to PaymentConfirmation", e);
        }
    }
}