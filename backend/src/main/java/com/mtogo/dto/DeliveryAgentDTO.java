package com.mtogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Delivery agent information including status and vehicle details")
public class DeliveryAgentDTO {

    @Schema(
        description = "Unique identifier of the delivery agent",
        example = "42"
    )
    private Long id;

    @Schema(
        description = "Full name of the delivery agent",
        example = "Sara Larsen"
    )
    private String name;

    @Schema(
        description = "Phone number used to contact the delivery agent",
        example = "+45 9876 5432"
    )
    private String phone;

    @Schema(
        description = "Type of vehicle used for deliveries",
        example = "Bike"
    )
    private String vehicleType;

    @Schema(
        description = "Whether the delivery agent is currently active and available",
        example = "true"
    )
    private boolean active;

    public DeliveryAgentDTO() {}

    public DeliveryAgentDTO(Long id, String name, String phone, String vehicleType, boolean active) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.vehicleType = vehicleType;
        this.active = active;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
