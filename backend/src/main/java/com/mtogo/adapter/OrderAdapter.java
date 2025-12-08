package com.mtogo.adapter;

import com.mtogo.dto.OrderDTO;
import com.mtogo.model.Order;

import java.util.stream.Collectors;

public class OrderAdapter {

    public static OrderDTO toDTO(Order order) {
        if (order == null) return null;

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setTotal(order.getTotal());

        dto.setCustomer(CustomerAdapter.toDTO(order.getCustomer()));
        dto.setRestaurant(RestaurantAdapter.toDTO(order.getRestaurant()));
        dto.setDeliveryAgent(DeliveryAgentAdapter.toDTO(order.getDeliveryAgent()));
        dto.setPayment(PaymentAdapter.toDTO(order.getPayment()));
        dto.setFeedback(FeedbackAdapter.toDTO(order.getFeedback()));

        if (order.getMenuItems() != null) {
            dto.setMenuItems(
                    order.getMenuItems().stream()
                            .map(MenuItemAdapter::toDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static Order toEntity(OrderDTO dto) {
        if (dto == null) return null;

        Order order = new Order();

        // Only set ID if updating an existing order
        if (dto.getId() != null) {
            order.setId(dto.getId());
        }

        order.setStatus(dto.getStatus());
        order.setTotal(dto.getTotal());

        // createdAt handled automatically by entity's default value
    if (dto.getMenuItems() != null) {
    order.setMenuItems(
        dto.getMenuItems().stream()
            .map(MenuItemAdapter::toEntity)
            .collect(Collectors.toList())
    );
}

        return order;
    }
}
