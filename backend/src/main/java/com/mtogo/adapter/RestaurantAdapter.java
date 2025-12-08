package com.mtogo.adapter;

import com.mtogo.dto.RestaurantDTO;
import com.mtogo.model.Restaurant;

import java.util.stream.Collectors;

public class RestaurantAdapter {

    public static RestaurantDTO toDTO(Restaurant restaurant) {
        if (restaurant == null) return null;

        RestaurantDTO dto = new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getEmail(),
                restaurant.getPhone(),
                restaurant.getAddress(),
                restaurant.isActive()
        );

        if (restaurant.getMenuItems() != null) {
            dto.setMenuItems(
                    restaurant.getMenuItems()
                            .stream()
                            .map(MenuItemAdapter::toDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static Restaurant toEntity(RestaurantDTO dto) {
        if (dto == null) return null;
        Restaurant restaurant = new Restaurant();
        restaurant.setId(dto.getId());
        restaurant.setName(dto.getName());
        restaurant.setEmail(dto.getEmail());
        restaurant.setPhone(dto.getPhone());
        restaurant.setAddress(dto.getAddress());
        restaurant.setActive(dto.isActive());
        return restaurant;
    }
}
