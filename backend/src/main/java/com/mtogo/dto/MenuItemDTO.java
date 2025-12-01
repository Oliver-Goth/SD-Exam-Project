package com.mtogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Represents a menu item offered by a restaurant, including price and availability")
public class MenuItemDTO {

    @Schema(
        description = "Unique identifier of the menu item",
        example = "12"
    )
    private Long id;

    @Schema(
        description = "Name of the menu item",
        example = "Margherita Pizza"
    )
    private String name;

    @Schema(
        description = "Short description of the menu item",
        example = "Classic Italian pizza with tomato, mozzarella, and basil"
    )
    private String description;

    @Schema(
        description = "Price of the menu item",
        example = "89.95"
    )
    private BigDecimal price;

    @Schema(
        description = "Whether the item is currently available for ordering",
        example = "true"
    )
    private boolean available;

    public MenuItemDTO() {}

    public MenuItemDTO(Long id, String name, String description, BigDecimal price, boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
