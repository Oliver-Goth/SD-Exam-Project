package com.mtogo.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request DTO for creating a new customer")
public class CreateCustomerRequestDTO {

    @Schema(description = "Full name of the customer", example = "John Andersen")
    private String name;

    @Schema(description = "Email address of the customer", example = "john.andersen@example.com")
    private String email;

    @Schema(description = "Password for the customer account", example = "StrongP@ssw0rd!")
    private String password;

    @Schema(description = "Phone number of the customer", example = "+45 1234 5678")
    private String phoneNumber;

    @Schema(description = "Date of birth of the customer", example = "1990-05-15")
    private LocalDate dateOfBirth;

    // Constructors
    public CreateCustomerRequestDTO() {
    }

    public CreateCustomerRequestDTO(String name, String email, String password,
            String phoneNumber, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    /**
     * Returns the full name provided by the client (or empty string if missing)
     */
    public String getFullName() {
        return name == null ? "" : name.trim();
    }
}
