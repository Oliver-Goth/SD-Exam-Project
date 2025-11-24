package com.mtogo.controller;

import com.mtogo.dto.MenuItemDTO;
import com.mtogo.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping("/restaurant/{restaurantId}")
    public List<MenuItemDTO> getMenuItemsByRestaurant(@PathVariable Long restaurantId) {
        return menuItemService.getMenuItemsByRestaurant(restaurantId);
    }

    @PostMapping("/restaurant/{restaurantId}")
    public MenuItemDTO createMenuItem(@RequestBody MenuItemDTO dto, @PathVariable Long restaurantId) {
        return menuItemService.createMenuItem(dto, restaurantId);
    }
}
