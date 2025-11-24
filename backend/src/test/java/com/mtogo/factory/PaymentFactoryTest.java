package com.mtogo.factory;

import com.mtogo.factory.PaymentFactory;
import com.mtogo.model.Order;
import com.mtogo.model.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PaymentFactoryTest {

    @Test
    void createPayment_createsPendingPayment() {
        Order order = new Order();
        BigDecimal amount = BigDecimal.valueOf(99.99);

        Payment payment = PaymentFactory.createPayment(order, amount, "CARD");

        assertNotNull(payment);
        assertEquals("CARD", payment.getMethod());
        assertEquals(amount, payment.getAmount());
        assertEquals("PENDING", payment.getStatus());
        assertEquals(order, payment.getOrder());
    }
}
