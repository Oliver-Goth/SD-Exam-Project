package com.mtogo.service;

import com.mtogo.adapter.PaymentAdapter;
import com.mtogo.dto.PaymentDTO;
import com.mtogo.factory.PaymentFactory;
import com.mtogo.model.Order;
import com.mtogo.model.Payment;
import com.mtogo.repository.OrderRepository;
import com.mtogo.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    // ✔ Constructor Injection = Better for testing
    public PaymentService(PaymentRepository paymentRepository,
                          OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Creates a payment for an existing order.
     */
    public PaymentDTO createPaymentForOrder(Long orderId, String method) {

        // ✔ Load real order from DB
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        // ✔ Use factory to build payment with correct total
        Payment payment = PaymentFactory.createPayment(order, order.getTotal(), method);

        // ✔ Save
        payment = paymentRepository.save(payment);

        return PaymentAdapter.toDTO(payment);
    }

    /**
     * Create a payment directly from a DTO.
     * (Used by your controller)
     */
    public PaymentDTO createPayment(PaymentDTO dto) {

        if (dto.getOrderId() == null) {
            throw new IllegalArgumentException("PaymentDTO must contain orderId");
        }

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        Payment payment = PaymentAdapter.toEntity(dto);
        payment.setOrder(order);

        payment = paymentRepository.save(payment);

        return PaymentAdapter.toDTO(payment);
    }

    public PaymentDTO getPaymentByOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        return PaymentAdapter.toDTO(payment);
    }
}
