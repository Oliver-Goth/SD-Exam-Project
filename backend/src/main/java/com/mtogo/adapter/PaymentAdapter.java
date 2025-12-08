package com.mtogo.adapter;

import com.mtogo.dto.PaymentDTO;
import com.mtogo.model.Payment;

public class PaymentAdapter {

    public static PaymentDTO toDTO(Payment payment) {
        if (payment == null) return null;
        return new PaymentDTO(
                payment.getId(),
                payment.getOrder() != null ? payment.getOrder().getId() : null,
                payment.getAmount(),
                payment.getMethod(),
                payment.getStatus(),
                payment.getCreatedAt()
        );
    }

    public static Payment toEntity(PaymentDTO dto) {
        if (dto == null) return null;
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setAmount(dto.getAmount());
        payment.setMethod(dto.getMethod());
        payment.setStatus(dto.getStatus());
        payment.setCreatedAt(dto.getCreatedAt());
        return payment;
    }
}
