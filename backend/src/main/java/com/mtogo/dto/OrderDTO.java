package com.mtogo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private Long id;
    private String status;
    private LocalDateTime createdAt;
    private BigDecimal total;

    private CustomerDTO customer;
    private RestaurantDTO restaurant;
    private DeliveryAgentDTO deliveryAgent;
    private PaymentDTO payment;
    private FeedbackDTO feedback;
    private List<MenuItemDTO> menuItems;

    public OrderDTO() {}

    // Getters & Setters
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
