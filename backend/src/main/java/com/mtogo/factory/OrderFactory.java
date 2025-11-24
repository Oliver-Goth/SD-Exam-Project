package com.mtogo.factory;

import com.mtogo.model.Order;
import com.mtogo.model.Restaurant;
import com.mtogo.model.Customer;
import java.math.BigDecimal;

public class OrderFactory {

    public static Order createOrder(Customer customer, Restaurant restaurant, BigDecimal total) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setTotal(total);
        order.setStatus("PENDING");
        // createdAt is set automatically by @PrePersist
        return order;
    }

    public static Order createDeliveryOrder(Customer customer, Restaurant restaurant, BigDecimal total) {
        Order order = createOrder(customer, restaurant, total);
        order.setStatus("PENDING");
        return order;
    }

    public static Order createPickupOrder(Customer customer, Restaurant restaurant, BigDecimal total) {
        Order order = createOrder(customer, restaurant, total);
        order.setStatus("PENDING");
        return order;
    }
}
