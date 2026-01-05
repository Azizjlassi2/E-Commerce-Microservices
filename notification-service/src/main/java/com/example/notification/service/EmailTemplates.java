package com.example.notification.service;

import lombok.Getter;

/**
 * EmailTemplates Enum defining email templates and their subjects for various
 * notification types.
 */
public enum EmailTemplates {

    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment Successfully processed "),
    ORDER_CONFIRMATION("order-confirmation.html", "Order Successfully processed ");

    @Getter
    private final String template;

    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = null;
        this.subject = null;
    }

}