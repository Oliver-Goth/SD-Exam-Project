package com.mtogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Represents a full customer order including items, payment, delivery, and feedback information")
public class OrderDTO {

    @Schema(
        description = "Unique identifier of the order",
        example = "5001"
    )
    private Long id;

    @Schema(
        description = "Current status of the order (Pending, Confirmed, Delivered, Cancelled)",
        example = "Confirmed"
    )
    private String status;

    @Schema(
        description = "Date and time when the order was created",
        example = "2025-04-22T18:45:00"
    )
    private LocalDateTime createdAt;

    @Schema(
        description = "Total price of the order including all items",
        example = "249.50"
    )
    private BigDecimal total;

    // ----------------------------- RELATED ENTITIES -----------------------------

    @Schema(
        description = "Customer who placed the order"
    )
    private CustomerDTO customer;

    @Schema(
        description = "Restaurant fulfilling the order"
    )
    private RestaurantDTO restaurant;

    @Schema(
        description = "Delivery agent assigned to deliver the order",
        nullable = true
    )
    private DeliveryAgentDTO deliveryAgent;

    @Schema(
        description = "Payment details associated with this order",
        nullable = true
    )
    private PaymentDTO payment;

    @Schema(
        description = "Customer feedback for this order, if any",
        nullable = true
    )
    private FeedbackDTO feedback;

    @Schema(
        description = "List of menu items included in this order"
    )
    private List<MenuItemDTO> menuItems;

    // ----------------------------- CONSTRUCTOR -----------------------------

    public OrderDTO() {}

    // ----------------------------- GETTERS & SETTERS -----------------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public CustomerDTO getCustomer() { return customer; }
    public void setCustomer(CustomerDTO customer) { this.customer = customer; }

    public RestaurantDTO getRestaurant() { return restaurant; }
    public void setRestaurant(RestaurantDTO restaurant) { this.restaurant = restaurant; }

    public DeliveryAgentDTO getDeliveryAgent() { return deliveryAgent; }
    public void setDeliveryAgent(DeliveryAgentDTO deliveryAgent) { this.deliveryAgent = deliveryAgent; }

    public PaymentDTO getPayment() { return payment; }
    public void setPayment(PaymentDTO payment) { this.payment = payment; }

    public FeedbackDTO getFeedback() { return feedback; }
    public void setFeedback(FeedbackDTO feedback) { this.feedback = feedback; }

    public List<MenuItemDTO> getMenuItems() { return menuItems; }
    public void setMenuItems(List<MenuItemDTO> menuItems) { this.menuItems = menuItems; }
}
