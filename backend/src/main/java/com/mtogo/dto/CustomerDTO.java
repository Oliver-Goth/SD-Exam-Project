package com.mtogo.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Customer data transfer object representing customer profile information")
public class CustomerDTO {

    @Schema(description = "Unique identifier of the customer", example = "101")
    private Long id;

    @Schema(description = "Full name of the customer", example = "John Andersen")
    private String name;

    @Schema(description = "Password of the customer", example = "StrongP@ssw0rd!")
    private String password;

    @Schema(description = "Email address of the customer", example = "john.andersen@example.com")
    private String email;

    @Schema(description = "Primary phone number of the customer", example = "+45 1234 5678")
    private String phoneNumber;

    @Schema(description = "Full date of birth of the customer", example = "Nørrebrogade 12, 2200 Copenhagen")
    private LocalDate dateOfBirth;

    // Constructors
    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String name, String password, String email, String phoneNumber, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
