package com.mtogo.service;

import com.mtogo.dto.PaymentDTO;
import com.mtogo.model.Order;
import com.mtogo.model.Payment;
import com.mtogo.repository.OrderRepository;
import com.mtogo.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;
    private PaymentService paymentService;

    @BeforeEach
    void setup() {
        paymentRepository = mock(PaymentRepository.class);
        orderRepository = mock(OrderRepository.class);

        paymentService = new PaymentService(paymentRepository, orderRepository);
    }

    // ----------------------------------------------------------
    // 1. Happy path: payment created successfully
    // ----------------------------------------------------------
    @Test
    void testCreatePaymentForOrder_success() {

        Long orderId = 1L;

        // Fake order from DB
        Order order = new Order();
        order.setId(orderId);
        order.setTotal(BigDecimal.valueOf(50));

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Payment savedPayment = new Payment();
        savedPayment.setId(10L);
        savedPayment.setOrder(order);
        savedPayment.setAmount(BigDecimal.valueOf(50));
        savedPayment.setMethod("CARD");

        when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);

        PaymentDTO dto = paymentService.createPaymentForOrder(orderId, "CARD");

        assertNotNull(dto);
        assertEquals(10L, dto.getId());
        assertEquals("CARD", dto.getMethod());
        assertEquals(BigDecimal.valueOf(50), dto.getAmount());

        verify(orderRepository).findById(orderId);
        verify(paymentRepository).save(any(Payment.class));
    }

    // ----------------------------------------------------------
    // 2. Order not found
    // ----------------------------------------------------------
    @Test
    void testCreatePaymentForOrder_orderNotFound() {

        Long orderId = 99L;

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> paymentService.createPaymentForOrder(orderId, "CARD")
        );

        assertEquals("Order not found", ex.getMessage());
    }

    // ----------------------------------------------------------
    // 3. Create payment from PaymentDTO
    // ----------------------------------------------------------
    @Test
    void testCreatePayment_fromDTO() {

        PaymentDTO dto = new PaymentDTO();
        dto.setOrderId(5L);
        dto.setAmount(BigDecimal.valueOf(100));
        dto.setMethod("MOBILEPAY");

        Order order = new Order();
        order.setId(5L);

        when(orderRepository.findById(5L)).thenReturn(Optional.of(order));

        Payment savedPayment = new Payment();
        savedPayment.setId(20L);
        savedPayment.setOrder(order);
        savedPayment.setAmount(dto.getAmount());
        savedPayment.setMethod(dto.getMethod());

        when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);

        PaymentDTO result = paymentService.createPayment(dto);

        assertNotNull(result);
        assertEquals(20L, result.getId());
        assertEquals("MOBILEPAY", result.getMethod());
        assertEquals(BigDecimal.valueOf(100), result.getAmount());

        verify(paymentRepository).save(any(Payment.class));
    }

    // ----------------------------------------------------------
    // 4. PaymentDTO missing orderId
    // ----------------------------------------------------------
    @Test
    void testCreatePayment_missingOrderId() {
        PaymentDTO dto = new PaymentDTO();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> paymentService.createPayment(dto)
        );

        assertEquals("PaymentDTO must contain orderId", ex.getMessage());
    }

    // ----------------------------------------------------------
    // 5. Get payment by orderId
    // ----------------------------------------------------------
    @Test
    void testGetPaymentByOrderId() {

        Long orderId = 1L;

        Payment payment = new Payment();
        payment.setId(30L);

        when(paymentRepository.findByOrderId(orderId)).thenReturn(payment);

        PaymentDTO dto = paymentService.getPaymentByOrderId(orderId);

        assertNotNull(dto);
        assertEquals(30L, dto.getId());
    }
}
