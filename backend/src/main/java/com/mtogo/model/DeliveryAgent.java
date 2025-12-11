package com.mtogo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "delivery_agents")
@Data
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String vehicleType;

    @Column
    private boolean active = true;

    @OneToMany(mappedBy = "deliveryAgent", cascade = CascadeType.ALL)
    private List<Order> orders;

    public DeliveryAgent() {}

    public DeliveryAgent(String name, String phone, String vehicleType) {
        this.name = name;
        this.phone = phone;
        this.vehicleType = vehicleType;
    }
}
