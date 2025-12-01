package com.mtogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Payment details for an order, including method, amount, and payment status")
public class PaymentDTO {

    @Schema(
        description = "Unique identifier of the payment record",
        example = "9001"
    )
    private Long id;

    @Schema(
        description = "ID of the order associated with the payment",
        example = "5001"
    )
    private Long orderId;

    @Schema(
        description = "Total amount paid for the order",
        example = "249.50"
    )
    private BigDecimal amount;

    @Schema(
        description = "Payment method used",
        example = "Credit Card"
    )
    private String method;

    @Schema(
        description = "Current payment status (Authorized, Failed, Completed, Refunded)",
        example = "Completed"
    )
    private String status;

    @Schema(
        description = "Timestamp when the payment was created",
        example = "2025-04-22T18:45:00"
    )
    private LocalDateTime createdAt;

    public PaymentDTO() {}

    public PaymentDTO(Long id, Long orderId, BigDecimal amount, String method, String status, LocalDateTime createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
