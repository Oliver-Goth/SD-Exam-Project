package com.mtogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Restaurant information including contact details, status, and menu items")
public class RestaurantDTO {

    @Schema(
        description = "Unique identifier of the restaurant",
        example = "2001"
    )
    private Long id;

    @Schema(
        description = "Name of the restaurant",
        example = "Nordic Sushi Bar"
    )
    private String name;

    @Schema(
        description = "Email address used by the restaurant",
        example = "contact@nordicsushi.dk"
    )
    private String email;

    @Schema(
        description = "Restaurant's primary phone number",
        example = "+45 4455 6677"
    )
    private String phone;

    @Schema(
        description = "Physical address of the restaurant",
        example = "Østerbrogade 55, 2100 Copenhagen"
    )
    private String address;

    @Schema(
        description = "Whether the restaurant is currently active and visible to customers",
        example = "true"
    )
    private boolean active;

    @Schema(
        description = "List of menu items offered by the restaurant"
    )
    private List<MenuItemDTO> menuItems;

    public RestaurantDTO() {}

    public RestaurantDTO(Long id, String name, String email, String phone, String address, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.active = active;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public List<MenuItemDTO> getMenuItems() { return menuItems; }
    public void setMenuItems(List<MenuItemDTO> menuItems) { this.menuItems = menuItems; }
}
