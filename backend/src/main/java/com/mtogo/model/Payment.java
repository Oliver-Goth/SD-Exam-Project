package com.mtogo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Payment Details ---
    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String method; // e.g., CASH, CARD, ONLINE

    @Column(nullable = false)
    private String status; // e.g., SUCCESS, FAILED, PENDING

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // --- Relationship ---
    // One payment belongs to one order
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    // --- Constructors ---
    public Payment() {}

    public Payment(BigDecimal amount, String method, String status, Order order) {
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.order = order;
    }

    // --- Getters & Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // --- toString() for debugging ---
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
