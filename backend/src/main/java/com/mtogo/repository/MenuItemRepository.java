package com.mtogo.repository;

import com.mtogo.model.MenuItem;
import com.mtogo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByRestaurant(Restaurant restaurant);
    List<MenuItem> findByAvailableTrue();
}
