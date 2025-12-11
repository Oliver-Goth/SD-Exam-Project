package com.mtogo.controller;

import com.mtogo.dto.MenuItemDTO;
import com.mtogo.service.MenuItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(
    name = "Menu Items",
    description = "Manage restaurant menu items including listing and creation"
)
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    // ------------------------------------------------------
    // GET /api/menu-items/restaurant/{restaurantId}
    // ------------------------------------------------------
    @GetMapping("/restaurant/{restaurantId}")
    @Operation(
        summary = "Get menu items for a restaurant",
        description = "Returns a list of all menu items belonging to the specified restaurant."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Menu items retrieved successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = MenuItemDTO.class)
        )
    )
    @ApiResponse(
        responseCode = "404",
        description = "Restaurant not found"
    )
    public List<MenuItemDTO> getMenuItemsByRestaurant(@PathVariable Long restaurantId) {
        return menuItemService.getMenuItemsByRestaurant(restaurantId);
    }

    // ------------------------------------------------------
    // POST /api/menu-items/restaurant/{restaurantId}
    // ------------------------------------------------------
    @PostMapping("/restaurant/{restaurantId}")
    @Operation(
        summary = "Create a menu item",
        description = "Creates a new menu item under the specified restaurant."
    )
    @ApiResponse(
        responseCode = "201",
        description = "Menu item created successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = MenuItemDTO.class)
        )
    )
    @ApiResponse(
        responseCode = "400",
        description = "Invalid menu item data"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Restaurant not found"
    )
    public MenuItemDTO createMenuItem(@RequestBody MenuItemDTO dto, @PathVariable Long restaurantId) {
        return menuItemService.createMenuItem(dto, restaurantId);
    }
}
