package com.mtogo.controller;

import com.mtogo.dto.RestaurantDTO;
import com.mtogo.service.RestaurantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(
    name = "Restaurants",
    description = "Restaurant registration, retrieval, and management"
)
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // ------------------------------------------------------
    // GET /api/restaurants
    // ------------------------------------------------------
    @GetMapping
    @Operation(
        summary = "Get all active restaurants",
        description = "Returns a list of restaurants that are approved and active in the system."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Restaurants retrieved successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = RestaurantDTO.class)
        )
    )
    public List<RestaurantDTO> getAllActiveRestaurants() {
        return restaurantService.getAllActiveRestaurants();
    }

    // ------------------------------------------------------
    // GET /api/restaurants/{id}
    // ------------------------------------------------------
    @GetMapping("/{id}")
    @Operation(
        summary = "Get restaurant by ID",
        description = "Returns detailed information about a specific restaurant by its ID."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Restaurant retrieved successfully"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Restaurant not found"
    )
    public RestaurantDTO getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    // ------------------------------------------------------
    // POST /api/restaurants
    // ------------------------------------------------------
    @PostMapping
    @Operation(
        summary = "Create a new restaurant",
        description = "Registers a new restaurant in the system. Requires approval to become active."
    )
    @ApiResponse(
        responseCode = "201",
        description = "Restaurant created successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = RestaurantDTO.class)
        )
    )
    @ApiResponse(
        responseCode = "400",
        description = "Invalid restaurant data"
    )
    public RestaurantDTO createRestaurant(@RequestBody RestaurantDTO dto) {
        return restaurantService.createRestaurant(dto);
    }

    // ------------------------------------------------------
    // DELETE /api/restaurants/{id}
    // ------------------------------------------------------
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a restaurant",
        description = "Removes a restaurant from the system by its ID."
    )
    @ApiResponse(
        responseCode = "204",
        description = "Restaurant deleted successfully"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Restaurant not found"
    )
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }
}
