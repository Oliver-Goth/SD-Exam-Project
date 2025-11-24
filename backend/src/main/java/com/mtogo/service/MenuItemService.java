package com.mtogo.service;

import com.mtogo.adapter.MenuItemAdapter;
import com.mtogo.dto.MenuItemDTO;
import com.mtogo.model.MenuItem;
import com.mtogo.model.Restaurant;
import com.mtogo.repository.MenuItemRepository;
import com.mtogo.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    // ✔ Constructor Injection (best practice)
    public MenuItemService(MenuItemRepository menuItemRepository,
                           RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<MenuItemDTO> getMenuItemsByRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        return menuItemRepository.findByRestaurant(restaurant)
                .stream()
                .map(MenuItemAdapter::toDTO)
                .collect(Collectors.toList());
    }

    public MenuItemDTO createMenuItem(MenuItemDTO dto, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        MenuItem item = MenuItemAdapter.toEntity(dto);
        item.setRestaurant(restaurant);

        return MenuItemAdapter.toDTO(menuItemRepository.save(item));
    }
}
