package com.mtogo.dto;

public class DeliveryAgentDTO {
    private Long id;
    private String name;
    private String phone;
    private String vehicleType;
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
