package com.mtogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Customer data transfer object representing customer profile information")
public class CustomerDTO {

    @Schema(
        description = "Unique identifier of the customer",
        example = "101"
    )
    private Long id;

    @Schema(
        description = "Full name of the customer",
        example = "John Andersen"
    )
    private String name;

    @Schema(
        description = "Email address of the customer",
        example = "john.andersen@example.com"
    )
    private String email;

    @Schema(
        description = "Primary phone number of the customer",
        example = "+45 1234 5678"
    )
    private String phone;

    @Schema(
        description = "Full address of the customer",
        example = "Nørrebrogade 12, 2200 Copenhagen"
    )
    private String address;

    // Constructors
    public CustomerDTO() {}

    public CustomerDTO(Long id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
               this.address = address;
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
}
