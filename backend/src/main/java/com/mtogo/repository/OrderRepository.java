package com.mtogo.repository;

import com.mtogo.model.Customer;
import com.mtogo.model.Order;
import com.mtogo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer(Customer customer);
    List<Order> findByRestaurant(Restaurant restaurant);
    List<Order> findByStatus(String status);
}
