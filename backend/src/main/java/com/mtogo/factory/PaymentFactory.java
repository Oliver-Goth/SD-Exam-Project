package com.mtogo.factory;

import com.mtogo.model.Order;
import com.mtogo.model.Payment;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentFactory {

    public static Payment createPayment(Order order, BigDecimal amount, String method) {
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(amount);
        payment.setMethod(method);
        payment.setStatus("PENDING");
        payment.setCreatedAt(LocalDateTime.now());
        return payment;
    }

    public static Payment createSuccessfulPayment(Order order, BigDecimal amount, String method) {
        Payment payment = createPayment(order, amount, method);
        payment.setStatus("SUCCESS");
        return payment;
    }

    public static Payment createFailedPayment(Order order, BigDecimal amount, String method) {
        Payment payment = createPayment(order, amount, method);
        payment.setStatus("FAILED");
        return payment;
    }
}
