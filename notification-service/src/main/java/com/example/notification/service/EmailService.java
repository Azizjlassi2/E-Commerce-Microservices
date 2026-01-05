package com.example.notification.service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.notification.consumer.order.Product;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * EmailService Handles the sending of various notification emails such as
 * payment confirmations and order confirmations using Thymeleaf templates.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender sender;
    private final SpringTemplateEngine engine;

    @Async
    public void sendPaymentSuccessEmail(String destination, String customerName,
            BigDecimal amount, String orderReference) throws MessagingException {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());

        helper.setFrom("azizjlassi498@gmail.com");
        final String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);

        helper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = engine.process(templateName, context);
            helper.setText(htmlTemplate, true);
            helper.setTo(destination);
            sender.send(message);
            log.info(String.format(
                    "INFO - Email Successfully sent to %s  with template %s", destination,
                    templateName));
        } catch (MessagingException e) {
            log.warn(String.format("WARNING - Cannot send email to %s", destination));
        }
    }

    @Async
    public void sendOrderConfirmationEmail(String destination, String customerName,
            BigDecimal amount, String orderReference, List<Product> products)
            throws MessagingException {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());

        helper.setFrom("azizjlassi498@gmail.com");
        final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);

        helper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = engine.process(templateName, context);
            helper.setText(htmlTemplate, true);
            helper.setTo(destination);
            sender.send(message);
            log.info(String.format(
                    "INFO - Email Successfully sent to %s  with template %s", destination,
                    templateName));
        } catch (MessagingException e) {
            log.warn(String.format("WARNING - Cannot send email to %s", destination));
        }
    }

}
